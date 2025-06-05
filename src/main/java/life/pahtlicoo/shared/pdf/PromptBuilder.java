package life.pahtlicoo.shared.pdf;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.historicdata.HistoricReportRequestDTO;
import life.pahtlicoo.domain.model.HistoricData;

import java.time.Month;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ApplicationScoped
public class PromptBuilder {

    public String buildPrompt(HistoricReportRequestDTO dto) {
        StringBuilder sb = new StringBuilder("Este es un informe de consumo histórico de medicamentos para centros de salud en México.\n");

        dto.getDataBySite().forEach((siteId, dataPorMes) -> {
            String siteName = dto.getSiteNameResolver().apply(siteId);
            sb.append("\nSitio: ").append(siteName).append("\n");

            Map<String, Integer> totalPorMedicamento = new HashMap<>();

            dataPorMes.forEach((mes, listaDatos) -> {
                for (HistoricData data : listaDatos) {
                    String medName = dto.getMedNameResolver().apply(data.getMedId());
                    int cantidad = data.getProjectedQuantity();
                    totalPorMedicamento.merge(medName, cantidad, Integer::sum);

                    sb.append("Mes ").append(getMonthName(data.getDateMonth()))
                            .append(", Medicamento: ").append(medName)
                            .append(", Proyectado: ").append(cantidad)
                            .append("\n");
                }
            });

            sb.append("Totales por medicamento:\n");
            totalPorMedicamento.forEach((med, total) ->
                    sb.append(" - ").append(med).append(": ").append(total).append("\n")
            );
        });

        sb.append("\nAnaliza si hay una tendencia de crecimiento o reducción por cada medicamento.\n");
        sb.append("No repitas los datos textualmente.\n");
        sb.append("Resume tu conclusión general en no más de 6 frases claras y concretas.\n");
        sb.append("Separa en párrafos");

        return sb.toString();
    }

    private String getMonthName(int monthNumber) {
        return Month.of(monthNumber).getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "ES"));
    }
}
