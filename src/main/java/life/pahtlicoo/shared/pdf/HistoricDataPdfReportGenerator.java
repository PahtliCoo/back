package life.pahtlicoo.shared.pdf;


import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
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

            Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
            Font subtitleFont = new Font(Font.HELVETICA, 12, Font.NORMAL);

            Paragraph title = new Paragraph("Reporte de Datos Históricos por Sitio", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph subtitle = new Paragraph("Año: " + dto.getYear() + ", Meses: " + dto.getStartMonth() + " a " + dto.getEndMonth(), subtitleFont);
            subtitle.setAlignment(Element.ALIGN_CENTER);
            document.add(subtitle);

            document.add(Chunk.NEWLINE);

            for (Map.Entry<Integer, Map<String, List<HistoricData>>> siteEntry : dto.getDataBySite().entrySet()) {
                Integer siteId = siteEntry.getKey();
                String siteName = dto.getSiteNameResolver().apply(siteId);

                Font siteFont = new Font(Font.HELVETICA, 13, Font.BOLD);
                Paragraph siteTitle = new Paragraph("Sitio: " + siteName, siteFont);
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

            Font conclusionFont = new Font(Font.HELVETICA, 12, Font.ITALIC);
            Paragraph conclusionTitle = new Paragraph("Conclusión del pronóstico", titleFont);
            conclusionTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(conclusionTitle);




            Paragraph conclusionBody = new Paragraph(conclusion, conclusionFont);

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
