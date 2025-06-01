package life.pahtlicoo.application.usecase.historicdata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.HistoricDataService;
import life.pahtlicoo.application.dto.historicdata.GetHistoricDataByDatesDTO;
import life.pahtlicoo.domain.model.HistoricData;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;


@ApplicationScoped
public class CreateReportWithHistoricDataUseCase {

    @Inject
    GetHistoricDataByDatesUseCase getHistoricDataByDatesUseCase;

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
            PdfPTable table = null;

            for (HistoricData data : sortedDataList) {
                if (previousSiteId == null || data.getSiteId() != previousSiteId) {
                    if (table != null) {
                        document.add(table);     // Agrega la tabla anterior
                        document.newPage();      // Salto de página
                    }
                    table = new PdfPTable(6);
                    table.addCell("Site");
                    table.addCell("Año");
                    table.addCell("Mes");
                    table.addCell("MedId");
                    table.addCell("Cantidad");
                    table.addCell("Proyectado");

                    previousSiteId = data.getSiteId();
                }

                table.addCell(String.valueOf(data.getSiteId()));
                table.addCell(String.valueOf(data.getDateYear()));
                table.addCell(String.valueOf(data.getDateMonth()));
                table.addCell(String.valueOf(data.getMedId()));
                table.addCell(String.valueOf(data.getQuantity()));
                table.addCell(String.valueOf(data.getProjectedQuantity()));
            }

            if (table != null) {
                document.add(table);
            }

            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }
}
