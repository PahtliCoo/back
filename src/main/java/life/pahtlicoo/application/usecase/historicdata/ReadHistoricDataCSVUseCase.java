package life.pahtlicoo.application.usecase.historicdata;

import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.historicdata.HistoricDataCSVDTO;
import life.pahtlicoo.application.dto.historicdata.SearchHistoricDataReqDTO;
import life.pahtlicoo.application.mapper.HistoricDataDomainMapper;
import life.pahtlicoo.application.service.*;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.domain.model.Med;
import life.pahtlicoo.domain.model.Site;


import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.List;

@ApplicationScoped
@Transactional
public class ReadHistoricDataCSVUseCase {
    @Inject
    HistoricDataService historicDataService;
    @Inject
    MedService medService;
    @Inject
    SiteService siteService;
    @Inject
    HistoricDataDomainMapper historicDataDomainMapper;

    public boolean execute(InputStream csvInputStream) {
        try{
            List<HistoricDataCSVDTO> historicDataCSVDTOList;
            // 1. Read all lines of the CSV.
            try (InputStreamReader reader = new InputStreamReader(csvInputStream)) {
                historicDataCSVDTOList = new CsvToBeanBuilder<HistoricDataCSVDTO>(reader)
                        .withType(HistoricDataCSVDTO.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withIgnoreEmptyLine(true)
                        .withSkipLines(0)
                        .build()
                        .parse();
            }catch (Exception e){
                System.out.println("Error en leer el csv");
                return false;
            }

            // 2. Check that the list is not empty
            if(historicDataCSVDTOList.isEmpty()){
                return false;
            }

            // 3. Pass through all the objects
            for(HistoricDataCSVDTO row : historicDataCSVDTOList) {
                try{
                    // 1. See if the med exists
                    Med med = medService.getMedByName(row.getMedName().toLowerCase());
                    if(med == null){
                        med = new Med();

                        med.setName(row.getMedName().toLowerCase());
                        System.out.println("Nombre que estamos guardando:" + med.getName());
                        medService.createMed(med);
                        System.out.println("Med que estamos recibiendio:" + med.getName());
                        med = medService.getMedByName(row.getMedName().toLowerCase());
                        System.out.println("Med que estamos recibiendio:" + med.getMedId());

                    }

                    System.out.println("Entrando a fase 2");
                    // 2. Get site name
                    Site site = siteService.findSiteByName(row.getSiteName().toLowerCase());
                    if(site == null){
                        return false;
                    }
                    System.out.println("Entrando a fase 3");
                    // 3. Review Historic Data
                    SearchHistoricDataReqDTO searchHistoricDataReqDTO = historicDataDomainMapper.searchHistoricDataToDomain(site,med,row);
                    HistoricData historicData = historicDataService.getHistoricDataBySiteIdAndMedIdAndDate(searchHistoricDataReqDTO);
                    System.out.println("Entrando a fase 4");
                    // 4. Create the historic data
                    if(historicData == null){
                        System.out.println("Entrando a fase 4.1");
                        historicData = historicDataDomainMapper.createHistoricDataDomainFromSearchHistoricData(searchHistoricDataReqDTO);
                        historicDataService.createHistoricData(historicData);
                    }else{
                        System.out.println("Entrando a fase 5");
                        // 5. Make update Changes to Quantity
                        historicData.setQuantity(row.getQuantity());
                        historicDataService.updateHistoricData(historicData);
                    }

                }catch (Exception e){
                    System.out.println("Error en leer el csv");
                }

            }
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
