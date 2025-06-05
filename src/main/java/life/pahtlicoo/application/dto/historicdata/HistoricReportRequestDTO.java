package life.pahtlicoo.application.dto.historicdata;

import life.pahtlicoo.domain.model.HistoricData;

import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;

public class HistoricReportRequestDTO {
    private final int year;
    private final int startMonth;
    private final int endMonth;
    private final String type;
    private final Map<Integer, Map<String, List<HistoricData>>> dataBySite;
    private final IntFunction<String> siteNameResolver;
    private final IntFunction<String> medNameResolver;

    public HistoricReportRequestDTO(
            int year,
            int startMonth,
            int endMonth,
            String type,
            Map<Integer, Map<String, List<HistoricData>>> dataBySite,
            IntFunction<String> siteNameResolver,
            IntFunction<String> medNameResolver
    ) {
        this.year = year;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.type = type;
        this.dataBySite = dataBySite;
        this.siteNameResolver = siteNameResolver;
        this.medNameResolver = medNameResolver;
    }

    public int getYear() {
        return year;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public String getType() {
        return type;
    }

    public Map<Integer, Map<String, List<HistoricData>>> getDataBySite() {
        return dataBySite;
    }

    public IntFunction<String> getSiteNameResolver() {
        return siteNameResolver;
    }

    public IntFunction<String> getMedNameResolver() {
        return medNameResolver;
    }
}
