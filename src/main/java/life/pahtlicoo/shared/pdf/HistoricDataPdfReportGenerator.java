package life.pahtlicoo.shared.pdf;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.historicdata.HistoricReportRequestDTO;
import life.pahtlicoo.domain.model.HistoricData;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class HistoricDataPdfReportGenerator {

    @Inject
    OpenAIServiceImp openAIServiceImp;
    @Inject
    TableBuilder tableBuilder;
    @Inject
    GraphBuilder graphBuilder;
    @Inject
    PromptBuilder promptBuilder;


    public byte[] generate(HistoricReportRequestDTO dto){
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            System.out.println("Este es el DTOOOO:");
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            Paragraph title = new Paragraph("Reporte de Datos Históricos por Sitio");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph subtitle = new Paragraph("Año: " + dto.getYear() + ", Meses: " + dto.getStartMonth() + " a " + dto.getEndMonth());
            subtitle.setAlignment(Element.ALIGN_CENTER);
            document.add(subtitle);

            document.add(Chunk.NEWLINE);

            for (Map.Entry<Integer, Map<String, List<HistoricData>>> siteEntry : dto.getDataBySite().entrySet()) {
                Integer siteId = siteEntry.getKey();
                String siteName = dto.getSiteNameResolver().apply(siteId);

                Paragraph siteTitle = new Paragraph("Sitio: " + siteName);
                siteTitle.setSpacingBefore(10f);
                siteTitle.setSpacingAfter(10f);
                document.add(siteTitle);
                document.add(Chunk.NEWLINE);

                if (dto.getType().equalsIgnoreCase("table") || dto.getType().equalsIgnoreCase("all")) {
                    List<PdfPTable> tablas = tableBuilder.buildTables(
                            siteEntry.getValue(),
                            siteName,
                            dto.getMedNameResolver()
                    );

                    for (PdfPTable tabla : tablas) {
                        document.add(tabla);
                        document.add(Chunk.NEWLINE);
                    }
                }

                if (dto.getType().equalsIgnoreCase("graph") || dto.getType().equalsIgnoreCase("all")) {
                    graphBuilder.addGraphsToDocument(document,  siteEntry.getValue(),  dto.getMedNameResolver()
                    );
                }
                document.newPage();
            }

            String prompt = promptBuilder.buildPrompt(dto);
            System.out.println(" Esto es lo enviado a chaaat:\n" + prompt);

            String conclusion = openAIServiceImp.reportConclusion(prompt);

            Paragraph conclusionTitle = new Paragraph("Conclusión del pronóstico");
            conclusionTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(conclusionTitle);

            Paragraph conclusionBody = new Paragraph(conclusion);

            conclusionBody.setSpacingBefore(10f);
            conclusionBody.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(conclusionBody);

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }


}
