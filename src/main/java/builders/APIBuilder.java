package builders;

import dtos.API;
import dtos.Elements;
import logService.LogMaskingService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class APIBuilder {

    private static final String TOP_ELEMENT_HEADER = "Method,URL,Frequency";
    private static final String ANALYTCIS_HEADER = "Method,URL,Min Time,Max Time,Average Time";


    public static API buildAPIDto(String log, LogMaskingService logMaskingService, Map<Elements,Integer> positions){
        String[] logELement = log.split(",");
        API api = new API();
        positions.forEach(((elements, pos) -> {
            build(elements,pos,logELement,api,logMaskingService);
        }));
        return api;
    }
    private static void build(Elements elements, int pos, String[] logELement,API api,LogMaskingService logMaskingService){
        switch (elements){
            case method: api.setMethod(logELement[pos]);
            break;
            case url:api.setMaskedUrl(logMaskingService.maskAllIntegersFromUrl(logELement[pos]));
            break;
            case response_time:api.setResponseTime(Integer.parseInt(logELement[pos]));
            break;
            default:
                break;
        }
    }

    public static List<String> buildTopElementsDto(API[] frequentItems, int currentCount) {
        List<String> topElements = new ArrayList<>();
        topElements.add(TOP_ELEMENT_HEADER);
        for (int i = 0; i < currentCount; i++) {
            topElements.add(joinString(frequentItems[i].getMethod(),frequentItems[i].getMaskedUrl(),frequentItems[i].getMetadata().getFrequency()));
        }
        return topElements;
    }

    public static List<String> buildTopElementsDto(Collection<API> frequentItems) {
        List<String> topElements = new ArrayList<>();
        topElements.add(TOP_ELEMENT_HEADER);
        for (API api:frequentItems) {
            topElements.add(joinString(api.getMethod(),api.getMaskedUrl(),api.getMetadata().getFrequency()));
        }
        return topElements;
    }

    private static String joinString(Object... data){
        return String.join(",", Arrays.toString(data));
    }

    public static List<String> buildAnalytics(Collection<API> values) {
        List<String> topElements = new ArrayList<>();
        topElements.add(ANALYTCIS_HEADER);
        for (API api:values) {
            topElements.add(joinString(api.getMethod(),api.getMaskedUrl(),api.getMetadata().getMinTime(),api.getMetadata().getMaxTIme(), (api.getMetadata().getSumOfTime()+0d)/api.getMetadata().getFrequency()));
        }
        return topElements;
    }
}
