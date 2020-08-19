package beanManager;

import logService.LogAnalyzerService;
import logService.LogAnalyzerServiceImpl;
import logService.LogMaskingService;
import logService.LogMaskingServiceImpl;

public class BeanManager {

    private static final LogAnalyzerService logAnalyzerService = new LogAnalyzerServiceImpl();
    private static final LogMaskingService logMaskingService = new LogMaskingServiceImpl();


    public static LogAnalyzerService getLogAnalyzerService(){
        return logAnalyzerService;
    }

    public static LogMaskingService getLogMaskingService() {
        return logMaskingService;
    }
}
