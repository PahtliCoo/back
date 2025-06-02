package life.pahtlicoo.application.usecase.historicdata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.historicdata.GetHistoricDataByDatesDTO;
import life.pahtlicoo.application.service.HistoricDataService;
import life.pahtlicoo.application.service.HistoricDataReportService;
import life.pahtlicoo.application.usecase.med.GetMedByIdUseCase;
import life.pahtlicoo.application.usecase.site.GetSiteByIdUseCase;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.shared.pdf.HistoricDataPdfReportGenerator;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class CreateReportWithHistoricDataUseCase {

    @Inject
    HistoricDataService historicDataService;

    @Inject
    HistoricDataReportService historicDataReportService;

    @Inject
    GetSiteByIdUseCase getSiteByIdUseCase;

    @Inject
    GetMedByIdUseCase getMedByIdUseCase;

    @Inject
    HistoricDataPdfReportGenerator pdfReportGenerator;

    public byte[] execute(GetHistoricDataByDatesDTO dto) {
        List<HistoricData> dataList = historicDataService.getHistoricDataByDateRange(
                dto.getYear(), dto.getStartMonth(), dto.getEndMonth(), dto.getType()
        );

        Map<Integer, Map<String, List<HistoricData>>> dataBySite = historicDataReportService.agruparPorSitioYMes(dataList);

        return pdfReportGenerator.generate(
                dataBySite,
                dto.getYear(),
                dto.getStartMonth(),
                dto.getEndMonth(),
                siteId -> getSiteByIdUseCase.execute(siteId),
                medId -> getMedByIdUseCase.execute(medId),
                dto.getType()
        );
    }
}
