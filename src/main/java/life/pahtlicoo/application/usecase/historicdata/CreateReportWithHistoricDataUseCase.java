package life.pahtlicoo.application.usecase.historicdata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.historicdata.GetHistoricDataByDatesDTO;
import life.pahtlicoo.application.usecase.med.GetMedByIdUseCase;
import life.pahtlicoo.application.usecase.site.GetSiteByIdUseCase;
import life.pahtlicoo.domain.model.HistoricData;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



import java.io.ByteArrayOutputStream;
import java.time.Month;
import java.util.Locale;
import java.awt.Color;


import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@ApplicationScoped
public class CreateReportWithHistoricDataUseCase {

    @Inject
    GetHistoricDataByDatesUseCase getHistoricDataByDatesUseCase;
    @Inject
    GetSiteByIdUseCase getSiteByIdUseCase;
    @Inject
    GetMedByIdUseCase getMedByIdUseCase;

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

            Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font subtitleFont = new Font(Font.HELVETICA, 12, Font.NORMAL);

            Paragraph title = new Paragraph("Reporte de Datos Históricos por Sitio", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph subtitle = new Paragraph("Año: " + dto.getYear() + ", Meses: " + dto.getStartMonth() + " a " + dto.getEndMonth(), subtitleFont);
            subtitle.setAlignment(Element.ALIGN_CENTER);
            document.add(subtitle);

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

                    Font siteFont = new Font(Font.HELVETICA, 13, Font.BOLD);
                    Paragraph siteTitle = new Paragraph("Sitio: " + siteName, siteFont);
                    siteTitle.setSpacingBefore(10f);
                    siteTitle.setSpacingAfter(10f);
                    document.add(siteTitle);

                    document.add(Chunk.NEWLINE);
                }

                PdfPTable table = new PdfPTable(6);
                table.setWidths(new float[]{2.5f, 1f, 1f, 1.5f, 1.5f, 1.5f});
                table.setWidthPercentage(100);

                // Estilo del encabezado
                Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
                Color headerBackground = new Color(230, 230, 230); // Gris claro
                // Encabezados centrados y con fondo
                String[] headers = {"Sitio", "Año", "Mes", "MedId", "Cantidad", "Proyectado"};
                for (String header : headers) {
                    PdfPCell headerCell = new PdfPCell(new Phrase(header, headerFont));
                    headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    headerCell.setBackgroundColor(headerBackground);
                    table.addCell(headerCell);
                }





                table.setWidths(new float[]{2.5f, 1f, 1f, 1.5f, 1.5f, 1.5f});
                table.setWidthPercentage(100);


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
                    if(i== 0) table.addCell(anioCell);
                    if (i == 0) table.addCell(mesCell);
                    String medName = getMedByIdUseCase.execute(data.getMedId());
                    PdfPCell medIdCell = new PdfPCell(new Phrase(medName));
                    medIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    medIdCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell quantityCell = new PdfPCell(new Phrase(String.valueOf(data.getQuantity())));
                    quantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    quantityCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    PdfPCell projectedCell = new PdfPCell(new Phrase(String.valueOf(data.getProjectedQuantity())));
                    projectedCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    projectedCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                    table.addCell(medIdCell);
                    table.addCell(quantityCell);
                    table.addCell(projectedCell);
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