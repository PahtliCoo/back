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
import java.util.*;

@ApplicationScoped
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

        // Tamaño del batch
        final int BATCH_SIZE = 50;

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
            List<HistoricData> newHistoricDataSet = new ArrayList<>();
            List<HistoricData> updateHistoricDataSet = new ArrayList<>();
            // Procesar todos los objetos de la lista
            for (int i = 0; i < historicDataCSVDTOList.size(); i++) {
                HistoricDataCSVDTO row = historicDataCSVDTOList.get(i);
                // 1. Obtener sitio
                String siteName = row.getSiteName();
                Site site = siteService.findSiteByName(siteName);
                if (site == null) {
                    return false;
                }

                // 2. Verificar si el medicamento existe
                String medName = row.getMedName();
                Med med = medService.getMedByName(medName);
                if (med == null) {
                    med = new Med();
                    med.setName(medName);
                    medService.createMed(med);
                    med = medService.getMedByName(medName);
                    if (med == null) {
                        return false;
                    }
                }

                HistoricData historicData = historicDataDomainMapper.createHistoricDataDomainFromSearchHistoricData(site,med,row);

                historicData = historicDataService.getHistoricDataBySiteIdAndMedIdAndDate(historicData);
                // 4. Crear o actualizar datos históricos
                if (historicData != null) {
                    historicData.setQuantity(row.getQuantity());
                    updateHistoricDataSet.add(historicData);

                }else{
                    historicData = historicDataDomainMapper.createHistoricDataDomainFromSearchHistoricData(site,med,row);
                    newHistoricDataSet.add(historicData);
                }

                // Procesar batches cuando alcancen el tamaño definido
                if ((i + 1) % BATCH_SIZE == 0 || i == historicDataCSVDTOList.size() - 1) {
                    // Procesar batch de nuevos registros
                    if (!newHistoricDataSet.isEmpty()) {
                        System.out.println("Procesando batch de nuevos registros: " + newHistoricDataSet.size());
                        historicDataService.createListOfHistoricData(newHistoricDataSet);
                        newHistoricDataSet.clear();
                    }

                    // Procesar batch de actualizaciones
                    if (!updateHistoricDataSet.isEmpty()) {
                        System.out.println("Procesando batch de actualizaciones: " + updateHistoricDataSet.size());
                        historicDataService.updateHistoricDataByDateMedSite(updateHistoricDataSet);
                        updateHistoricDataSet.clear();
                    }
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}