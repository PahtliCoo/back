package life.pahtlicoo.shared.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.HistoricData;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;

@ApplicationScoped
public class TableBuilder {

    public List<PdfPTable> buildTables(
            Map<String, List<HistoricData>> dataPorMes,
            String siteName,
            IntFunction<String> medNameResolver
    ) throws DocumentException {

        List<PdfPTable> tablas = new ArrayList<>();

        for (List<HistoricData> grupo : dataPorMes.values()) {
            if (grupo.isEmpty()) continue;

            HistoricData first = grupo.get(0);

            PdfPTable table = new PdfPTable(6);
            table.setWidths(new float[]{2.5f, 1f, 1f, 1.5f, 1.5f, 1.5f});
            table.setWidthPercentage(100);

            Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            Color headerBackground = new Color(230, 230, 230);
            String[] headers = {"Sitio", "Año", "Mes", "Medicamento", "Cantidad", "Proyectado"};

            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(headerBackground);
                table.addCell(cell);
            }

            PdfPCell siteCell = createMergedCell(siteName, grupo.size());
            PdfPCell yearCell = createMergedCell(String.valueOf(first.getDateYear()), grupo.size());
            PdfPCell monthCell = createMergedCell(getMonthName(first.getDateMonth()), grupo.size());

            for (int i = 0; i < grupo.size(); i++) {
                HistoricData data = grupo.get(i);
                if (i == 0) {
                    table.addCell(siteCell);
                    table.addCell(yearCell);
                    table.addCell(monthCell);
                }

                table.addCell(createCenteredCell(medNameResolver.apply(data.getMedId())));
                table.addCell(createCenteredCell(String.valueOf(data.getQuantity())));
                table.addCell(createCenteredCell(String.valueOf(data.getProjectedQuantity())));
            }

            tablas.add(table);
        }

        return tablas;
    }

    private PdfPCell createMergedCell(String content, int rowspan) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setRowspan(rowspan);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    private PdfPCell createCenteredCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    private String getMonthName(int monthNumber) {
        return java.time.Month.of(monthNumber)
                .getDisplayName(java.time.format.TextStyle.FULL, new java.util.Locale("es", "ES"));
    }
}
