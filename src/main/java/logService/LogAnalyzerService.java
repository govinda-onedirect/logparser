package logService;

import dtos.API;

public interface LogAnalyzerService {

    void analyze(API api, Integer responseTime);
}
