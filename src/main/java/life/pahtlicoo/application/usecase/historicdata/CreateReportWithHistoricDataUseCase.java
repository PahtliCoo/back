package life.pahtlicoo.application.usecase.historicdata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.HistoricDataService;
import life.pahtlicoo.application.dto.historicdata.GetHistoricDataByDatesDTO;
import life.pahtlicoo.application.usecase.site.GetSiteByIdUseCase;
import life.pahtlicoo.domain.model.HistoricData;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


import java.io.ByteArrayOutputStream;
import java.time.Month;
import java.util.Locale;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@ApplicationScoped
public class CreateReportWithHistoricDataUseCase {

    @Inject
    GetHistoricDataByDatesUseCase getHistoricDataByDatesUseCase;
    @Inject
    GetSiteByIdUseCase getSiteByIdUseCase;

    private String getNombreMes(int numeroMes) {
        return Month.of(numeroMes).getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "ES"));
    }

    public byte[] execute(GetHistoricDataByDatesDTO dto) {
        List<HistoricData> dataList = getHistoricDataByDatesUseCase.execute(dto);

        List<HistoricData> sortedDataList = new java.util.ArrayList<>(dataList);
        sortedDataList.sort((a, b) -> {
            int bySite = Integer.compare(a.getSiteId(), b.getSiteId());
            if (bySite != 0) return bySite;
            int byMonth = Integer.compare(a.getDateMonth(), b.getDateMonth());
            if (byMonth != 0) return byMonth;
            return Integer.compare(a.getMedId(), b.getMedId());
        });

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            document.add(new Paragraph("Reporte de Datos Históricos por Sitio"));
            document.add(new Paragraph("Año: " + dto.getYear() + ", Meses: " + dto.getStartMonth() + " a " + dto.getEndMonth()));
            document.add(Chunk.NEWLINE);

            Integer previousSiteId = null;

            // Agrupar por sitio, año y mes
            Map<String, List<HistoricData>> grouped = sortedDataList.stream()
                    .collect(Collectors.groupingBy(d -> d.getSiteId() + "-" + d.getDateYear() + "-" + d.getDateMonth(),
                            LinkedHashMap::new, Collectors.toList()));

            for (List<HistoricData> group : grouped.values()) {
                HistoricData first = group.get(0);
                String siteName = getSiteByIdUseCase.execute(first.getSiteId());
                boolean isNewSite = previousSiteId == null || first.getSiteId() != previousSiteId;
                if (isNewSite) {
                    if (previousSiteId != null) {
                        document.newPage();
                    }
                    document.add(new Paragraph("Sitio: " + siteName));
                    document.add(Chunk.NEWLINE);
                }

                PdfPTable table = new PdfPTable(6);
                table.setWidths(new float[]{2.5f, 1f, 1f, 1.5f, 1.5f, 1.5f});
                table.setWidthPercentage(100);
                table.addCell("Sitio");
                table.addCell("Año");
                table.addCell("Mes");
                table.addCell("MedId");
                table.addCell("Cantidad");
                table.addCell("Proyectado");

                PdfPCell mesCell = new PdfPCell(new Phrase(getNombreMes(first.getDateMonth())));
                mesCell.setRowspan(group.size());
                mesCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                PdfPCell anioCell = new PdfPCell(new Phrase(String.valueOf(first.getDateYear())));
                anioCell.setRowspan(group.size());
                anioCell.setVerticalAlignment(Element.ALIGN_MIDDLE);


                for (int i = 0; i < group.size(); i++) {
                    HistoricData data = group.get(i);
                    table.addCell(siteName);
                    if(i== 0) table.addCell(anioCell);
                    if (i == 0) table.addCell(mesCell);
                    table.addCell(String.valueOf(data.getMedId()));
                    table.addCell(String.valueOf(data.getQuantity()));
                    table.addCell(String.valueOf(data.getProjectedQuantity()));
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
}