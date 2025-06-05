package life.pahtlicoo.shared.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Chunk;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.service.HistoricDataReportService;
import life.pahtlicoo.domain.model.HistoricData;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;

@ApplicationScoped
public class GraphBuilder {

    private final HistoricDataReportService historicDataReportService;

    public GraphBuilder(HistoricDataReportService historicDataReportService) {
        this.historicDataReportService = historicDataReportService;
    }

    public void addGraphsToDocument(Document document,
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
                    "Meses",
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

            chartImage.setAlignment(Image.ALIGN_CENTER);
            chartImage.scaleToFit(400, 300);

            document.add(Chunk.NEWLINE);
            document.add(new Paragraph(medicamento));
            document.add(chartImage);
        }

        document.newPage();
    }
}
