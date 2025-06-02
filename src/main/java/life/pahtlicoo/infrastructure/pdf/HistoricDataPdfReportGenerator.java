package life.pahtlicoo.infrastructure.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.HistoricData;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.time.Month;
import java.util.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class HistoricDataPdfReportGenerator {

    public byte[] generate(List<HistoricData> sortedDataList, int year, int startMonth, int endMonth,
                           java.util.function.IntFunction<String> siteNameResolver,
                           java.util.function.IntFunction<String> medNameResolver) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font subtitleFont = new Font(Font.HELVETICA, 12, Font.NORMAL);

            Paragraph title = new Paragraph("Reporte de Datos Históricos por Sitio", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph subtitle = new Paragraph("Año: " + year + ", Meses: " + startMonth + " a " + endMonth, subtitleFont);
            subtitle.setAlignment(Element.ALIGN_CENTER);
            document.add(subtitle);

            document.add(Chunk.NEWLINE);

            Integer previousSiteId = null;

            Map<String, List<HistoricData>> grouped = sortedDataList.stream()
                    .collect(Collectors.groupingBy(d -> d.getSiteId() + "-" + d.getDateYear() + "-" + d.getDateMonth(),
                            LinkedHashMap::new, Collectors.toList()));

            for (List<HistoricData> group : grouped.values()) {
                HistoricData first = group.get(0);
                String siteName = siteNameResolver.apply(first.getSiteId());

                boolean isNewSite = previousSiteId == null || first.getSiteId() != previousSiteId;
                if (isNewSite && previousSiteId != null) document.newPage();

                Font siteFont = new Font(Font.HELVETICA, 13, Font.BOLD);
                Paragraph siteTitle = new Paragraph("Sitio: " + siteName, siteFont);
                siteTitle.setSpacingBefore(10f);
                siteTitle.setSpacingAfter(10f);
                document.add(siteTitle);
                document.add(Chunk.NEWLINE);

                PdfPTable table = new PdfPTable(6);
                table.setWidths(new float[]{2.5f, 1f, 1f, 1.5f, 1.5f, 1.5f});
                table.setWidthPercentage(100);

                Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
                Color headerBackground = new Color(230, 230, 230);
                String[] headers = {"Sitio", "Año", "Mes", "MedId", "Cantidad", "Proyectado"};
                for (String header : headers) {
                    PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(headerBackground);
                    table.addCell(cell);
                }

                PdfPCell mesCell = new PdfPCell(new Phrase(getNombreMes(first.getDateMonth())));
                mesCell.setRowspan(group.size());
                mesCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                mesCell.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell anioCell = new PdfPCell(new Phrase(String.valueOf(first.getDateYear())));
                anioCell.setRowspan(group.size());
                anioCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                anioCell.setHorizontalAlignment(Element.ALIGN_CENTER);

                PdfPCell siteCell = new PdfPCell(new Phrase(siteName));
                siteCell.setRowspan(group.size());
                siteCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                siteCell.setHorizontalAlignment(Element.ALIGN_CENTER);

                for (int i = 0; i < group.size(); i++) {
                    HistoricData data = group.get(i);
                    if (i == 0) table.addCell(siteCell);
                    if (i == 0) table.addCell(anioCell);
                    if (i == 0) table.addCell(mesCell);

                    String medName = medNameResolver.apply(data.getMedId());
                    table.addCell(centeredCell(medName));
                    table.addCell(centeredCell(String.valueOf(data.getQuantity())));
                    table.addCell(centeredCell(String.valueOf(data.getProjectedQuantity())));
                }

                document.add(table);
                document.add(Chunk.NEWLINE);
                previousSiteId = first.getSiteId();
            }

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }

    private String getNombreMes(int numeroMes) {
        return Month.of(numeroMes).getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "ES"));
    }

    private PdfPCell centeredCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }
}
