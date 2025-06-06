package life.pahtlicoo.application.usecase.historicdata;

import com.opencsv.bean.CsvToBean;
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

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // Header names and variables
        Map<String, Integer> headersEsperados = new HashMap<>();
        headersEsperados.put("nombre_medicamento", 0);
        headersEsperados.put("cantidad_medicamento", 1);
        headersEsperados.put("nombre_hospital", 2);
        headersEsperados.put("número_mes", 3);
        headersEsperados.put("año", 4);

        try {
            // Obtain all the values
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(csvInputStream));
            String headerLine = bufferedReader.readLine();

            if (headerLine == null) {
                return false;
            }

            // Check and parse headers
            String[] headersLeidosArray = headerLine.split(",");
            Map<String, Integer> headersLeidos = new HashMap<>();
            for (int i = 0; i < headersLeidosArray.length; i++) {
                headersLeidos.put(headersLeidosArray[i].trim(), i);
            }

            // Check the headers
            if (!headersLeidos.keySet().containsAll(headersEsperados.keySet())) {
                return false;
            }

            // Obtain all the remaining data
            CsvToBean<HistoricDataCSVDTO> csvToBean = new CsvToBeanBuilder<HistoricDataCSVDTO>(bufferedReader)
                    .withType(HistoricDataCSVDTO.class)
                    .build();

            List<HistoricDataCSVDTO> historicDataCSVDTOList = csvToBean.parse();

            // Check that list is not empty
            if (historicDataCSVDTOList.isEmpty()) {
                return false;
            }

            // Procesar todos los objetos de la lista
            for (HistoricDataCSVDTO row : historicDataCSVDTOList) {
                try {
                    // 1. Verificar si el medicamento existe
                    Med med = medService.getMedByName(row.getMedName().toLowerCase());
                    if (med == null) {
                        med = new Med();
                        med.setName(row.getMedName().toLowerCase());
                        medService.createMed(med);
                        med = medService.getMedByName(row.getMedName().toLowerCase());
                    }

                    // 2. Obtener sitio
                    Site site = siteService.findSiteByName(row.getSiteName().toLowerCase());
                    if (site == null) {
                        return false;
                    }

                    // 3. Revisar datos históricos
                    SearchHistoricDataReqDTO searchHistoricDataReqDTO = historicDataDomainMapper.searchHistoricDataToDomain(site, med, row);
                    HistoricData historicData = historicDataService.getHistoricDataBySiteIdAndMedIdAndDate(searchHistoricDataReqDTO);

                    // 4. Crear o actualizar datos históricos
                    if (historicData == null) {
                        historicData = historicDataDomainMapper.createHistoricDataDomainFromSearchHistoricData(searchHistoricDataReqDTO);
                        historicDataService.createHistoricData(historicData);
                    } else {
                        historicData.setQuantity(row.getQuantity());
                        historicDataService.updateHistoricData(historicData);
                    }
                } catch (Exception e) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}