package logService;

import beanManager.BeanManager;
import builders.APIBuilder;
import dtos.API;
import dtos.Elements;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogStreamProcessorImpl implements LogStreamProcessor{

    private final TopElements topElements;
    private final Map<String,API> allAPIs = new HashMap<>();
    private final Map<Elements,Integer> headerPositions = new HashMap<>();

    public LogStreamProcessorImpl(int size) {
        topElements =  new TopElements(size);
    }

    @Override
    public void processLog(String log,String header) {
        populateHeaderPos(header);
        if (LogValidatorService.validateLog(log,headerPositions)) {
            API api = APIBuilder.buildAPIDto(log, BeanManager.getLogMaskingService(), headerPositions);
            boolean old = true;
            API existingAPI = allAPIs.getOrDefault(api.getAPIKey(),api);
            BeanManager.getLogAnalyzerService().analyze(existingAPI, api.getResponseTime());
            allAPIs.put(api.getAPIKey(), existingAPI);
            topElements.fill(existingAPI);
        }
    }

    private void populateHeaderPos(String header){
        if (headerPositions.size()==0){
            String[] headerElements = header.split(",");
            for (int i = 0; i < headerElements.length; i++) {
                String headerElement = headerElements[i];
                Elements elements = Elements.getElements(headerElement);
                if (elements != null) {
                    headerPositions.put(elements, i);
                }
            }
        }
    }


    @Override
    public List<String> getTopNElements(int n) {
        return APIBuilder.buildTopElementsDto(allAPIs.values().stream().sorted().collect(Collectors.toList()));
       }

    @Override
    public List<String> getTopNElements() {
        return APIBuilder.buildTopElementsDto(topElements.getFrequentItems().values());
    }

    @Override
    public List<String> getAnalytics() {
        return APIBuilder.buildAnalytics(allAPIs.values());
    }



}
