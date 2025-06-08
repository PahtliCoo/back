package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.historicdata.GenerateForecastReqDTO;
import life.pahtlicoo.application.dto.historicdata.GenerateForecastResDTO;
import life.pahtlicoo.application.dto.historicdata.PredictionDTO;
import life.pahtlicoo.domain.model.HistoricData;
import life.pahtlicoo.domain.repository.HistoricDataRepository;
import life.pahtlicoo.shared.util.HoltWinters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ForecastService {
    @Inject
    HistoricDataRepository historicDataRepository;

    //Definición de las enfermedades/medicamentos que son estacionales y no estacionales
    private final Set<Integer> MULTIPLICATIVE_MEDS = Set.of(1, 2, 4, 5, 7, 8);
    private final Set<Integer> ADDITIVE_MEDS = Set.of(3, 6);

    public List<GenerateForecastResDTO> generateForecasts(GenerateForecastReqDTO generateForecastReqDTO) {
        List<GenerateForecastResDTO> results = new ArrayList<>();

        for (Integer siteId : generateForecastReqDTO.getSite_ids()) {
            for (Integer medId : generateForecastReqDTO.getMed_ids()) {

                // Buscar todos los datos disponibles, ordenados
                List<HistoricData> pastData = historicDataRepository.getAllHistoricDataBySiteIdAndMedId(siteId, medId);

                if (pastData.size() < 2) {
                    results.add(new GenerateForecastResDTO(siteId, medId, false,
                            "No hay suficiente información de este medicamento (Se requieren al menos 2 meses)",
                            new ArrayList<>()));
                    continue;
                }

                pastData.sort(Comparator.comparing(HistoricData::getDateYear)
                        .thenComparing(HistoricData::getDateMonth));
                int[] values = pastData.stream().mapToInt(HistoricData::getQuantity).toArray();

                HoltWinters model = new HoltWinters(0.5, 0.3, 0.2, 12, generateForecastReqDTO.getForecast_horizon());
                int[] forecast;
                String chosenModel;
                try {
                    if (MULTIPLICATIVE_MEDS.contains(medId)) {
                        forecast = model.multiplicativeForecast(values);
                        chosenModel = "multiplicative";
                    } else if (ADDITIVE_MEDS.contains(medId)) {
                        forecast = model.aditiveForecast(values);
                        chosenModel = "additive";
                    } else {
                        //Si no tiene asignado, se considera poca estacionalidad y se usa aditivo
                        forecast = model.aditiveForecast(values);
                        chosenModel = "additive";
                    }
                } catch (Exception e) {
                    results.add(new GenerateForecastResDTO(siteId, medId, false,
                            "Model error: " + e.getMessage(), new ArrayList<>()));
                    continue;
                }

                // Obtener el último año/mes conocido
                //HistoricData last = pastData.get(pastData.size() - 1);
                //int[] date = {last.getDateMonth(), last.getDateYear()};

                //Obtener a partir de los datos recibidos
                int[] date = {generateForecastReqDTO.getCurrent_month(), generateForecastReqDTO.getCurrent_year()};
                System.out.println("[Forecast] Using reference date for prediction start: " + date[1] + "-" + date[0]);

                List<PredictionDTO> predictionList = new ArrayList<>();
                List<HistoricData> toSave = new ArrayList<>();

                for (int value : forecast) {
                    nextMonth(date);
                    int month = date[0];
                    int year = date[1];

                    predictionList.add(new PredictionDTO(year, month, value));

                    if (generateForecastReqDTO.isSave()) {
                        HistoricData newData = new HistoricData();
                        newData.setSiteId(siteId);
                        newData.setMedId(medId);
                        newData.setDateYear(year);
                        newData.setDateMonth(month);
                        newData.setQuantity(0); // real = 0
                        newData.setProjectedQuantity(value);
                        toSave.add(newData);
                    }
                }

                if (generateForecastReqDTO.isSave() && !toSave.isEmpty()) {
                    System.out.println("[Forecast] Saving " + toSave.size() + " predictions for site " + siteId + ", med " + medId);
                    historicDataRepository.createOrUpdateForecastData(toSave);
                }

                results.add(new GenerateForecastResDTO(siteId, medId, generateForecastReqDTO.isSave(),
                        "OK (" + chosenModel + " model)", predictionList));
            }
        }
        return results;
    }

    private void nextMonth(int[] date) {
        if (++date[0] > 12) {
            date[0] = 1;
            date[1]++;
        }
    }
}
