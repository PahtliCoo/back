package life.pahtlicoo.shared.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.HistoricDataReportService;
import life.pahtlicoo.domain.model.HistoricData;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.Month;
import java.util.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.IntFunction;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

@ApplicationScoped
public class HistoricDataPdfReportGenerator {

    @Inject
    HistoricDataReportService historicDataReportService;

    public byte[] generate(Map<Integer, Map<String, List<HistoricData>>> dataBySite, int year, int startMonth, int endMonth,
                           IntFunction<String> siteNameResolver,
                           IntFunction<String> medNameResolver,
                           String type) {
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

            for (Map.Entry<Integer, Map<String, List<HistoricData>>> siteEntry : dataBySite.entrySet()) {
                Integer siteId = siteEntry.getKey();
                String siteName = siteNameResolver.apply(siteId);

                Font siteFont = new Font(Font.HELVETICA, 13, Font.BOLD);
                Paragraph siteTitle = new Paragraph("Sitio: " + siteName, siteFont);
                siteTitle.setSpacingBefore(10f);
                siteTitle.setSpacingAfter(10f);
                document.add(siteTitle);
                document.add(Chunk.NEWLINE);

                if (type.equalsIgnoreCase("table") || type.equalsIgnoreCase("all")) {                    for (List<HistoricData> group : siteEntry.getValue().values()) {
                        HistoricData first = group.get(0);

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

                        PdfPCell siteCell = new PdfPCell(new Phrase(siteName));
                        siteCell.setRowspan(group.size());
                        siteCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        siteCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                        PdfPCell yearCell = new PdfPCell(new Phrase(String.valueOf(first.getDateYear())));
                        yearCell.setRowspan(group.size());
                        yearCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        yearCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                        PdfPCell monthCell = new PdfPCell(new Phrase(getNombreMes(first.getDateMonth())));
                        monthCell.setRowspan(group.size());
                        monthCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        monthCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                        for (int i = 0; i < group.size(); i++) {
                            HistoricData data = group.get(i);
                            if (i == 0) table.addCell(siteCell);
                            if (i == 0) table.addCell(yearCell);
                            if (i == 0) table.addCell(monthCell);

                            table.addCell(centeredCell(medNameResolver.apply(data.getMedId())));
                            table.addCell(centeredCell(String.valueOf(data.getQuantity())));
                            table.addCell(centeredCell(String.valueOf(data.getProjectedQuantity())));
                        }

                        document.add(table);
                        document.add(Chunk.NEWLINE);
                    }
                }

                if (type.equalsIgnoreCase("graph") || type.equalsIgnoreCase("all")) {
                    agregarGraficasPorMedicamento(document, siteEntry.getValue(), medNameResolver, siteName);
                }

                document.newPage();
            }

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }

    private void agregarGraficasPorMedicamento(Document document,
                                               Map<String, List<HistoricData>> datosPorMes,
                                               IntFunction<String> medNameResolver,
                                               String siteName) throws Exception {

        Map<String, Map<String, Integer>> dataPorMedicamento =
                historicDataReportService.agruparPorMedicamento(datosPorMes, medNameResolver::apply);

        for (Map.Entry<String, Map<String, Integer>> entry : dataPorMedicamento.entrySet()) {
            String medicamento = entry.getKey();
            Map<String, Integer> cantidadesPorMes = entry.getValue();

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (Map.Entry<String, Integer> mesData : cantidadesPorMes.entrySet()) {
                dataset.addValue(mesData.getValue(), medicamento, mesData.getKey());
            }

            JFreeChart chart = ChartFactory.createBarChart(
                    "Consumo mensual - " + medicamento,
                    "Meses" ,
                    "Cantidad",
                    dataset,
                    PlotOrientation.VERTICAL,
                    false, false, false
            );

            chart.setBackgroundPaint(Color.WHITE);
            chart.getPlot().setBackgroundPaint(Color.WHITE);
            chart.getPlot().setOutlineVisible(false);
            chart.getCategoryPlot().setRangeGridlinesVisible(false);
            chart.getCategoryPlot().setDomainGridlinesVisible(false);
            chart.getTitle().setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 14));

            BufferedImage bufferedImage = chart.createBufferedImage(500, 350);
            ByteArrayOutputStream chartBaos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", chartBaos);
            Image chartImage = Image.getInstance(chartBaos.toByteArray());

            chartImage.setAlignment(Element.ALIGN_CENTER);
            chartImage.scaleToFit(400, 300);

            document.add(Chunk.NEWLINE);
            document.add(chartImage);
        }

        document.newPage();
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
