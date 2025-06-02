package life.pahtlicoo.application.usecase.historicdata;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.historicdata.GetHistoricDataByDatesDTO;
import life.pahtlicoo.application.usecase.med.GetMedByIdUseCase;
import life.pahtlicoo.application.usecase.site.GetSiteByIdUseCase;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.infrastructure.pdf.HistoricDataPdfReportGenerator;

import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class CreateReportWithHistoricDataUseCase {

    @Inject
    GetHistoricDataByDatesUseCase getHistoricDataByDatesUseCase;

    @Inject
    GetSiteByIdUseCase getSiteByIdUseCase;

    @Inject
    GetMedByIdUseCase getMedByIdUseCase;

    @Inject
    HistoricDataPdfReportGenerator pdfReportGenerator;

    public byte[] execute(GetHistoricDataByDatesDTO dto) {
        List<HistoricData> dataList = new java.util.ArrayList<>(getHistoricDataByDatesUseCase.execute(dto));
        dataList.sort(Comparator.comparing(HistoricData::getSiteId)
                .thenComparing(HistoricData::getDateMonth)
                .thenComparing(HistoricData::getMedId));

        return pdfReportGenerator.generate(
                dataList,
                dto.getYear(),
                dto.getStartMonth(),
                dto.getEndMonth(),
                siteId -> getSiteByIdUseCase.execute(siteId),
                medId -> getMedByIdUseCase.execute(medId)
        );
    }
}
