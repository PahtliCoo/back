package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.HistoricData;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
public class HistoricDataReportService {

    public Map<Integer, Map<String, List<HistoricData>>> agruparPorSitioYMes(List<HistoricData> dataList) {
        return dataList.stream()
                .collect(Collectors.groupingBy(
                        HistoricData::getSiteId,
                        LinkedHashMap::new,
                        Collectors.groupingBy(
                                d -> d.getDateYear() + "-" + d.getDateMonth(),
                                LinkedHashMap::new,
                                Collectors.toList()
                        )
                ));
    }

    public Map<String, Map<String, Integer>> agruparPorMedicamento(Map<String, List<HistoricData>> datosPorMes, Function<Integer, String> medNameResolver) {
        Map<String, Map<String, Integer>> resultado = new LinkedHashMap<>();

        for (Map.Entry<String, List<HistoricData>> entry : datosPorMes.entrySet()) {
            String[] partes = entry.getKey().split("-");
            String mes = partes[1];

            for (HistoricData data : entry.getValue()) {
                String medicamento = medNameResolver.apply(data.getMedId());
                resultado
                        .computeIfAbsent(medicamento, k -> new LinkedHashMap<>())
                        .merge(mes, data.getQuantity(), Integer::sum);
            }
        }

        return resultado;
    }
}
