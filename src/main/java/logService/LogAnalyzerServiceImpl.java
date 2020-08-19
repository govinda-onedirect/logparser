package logService;

import dtos.API;
import dtos.Metadata;

import java.util.Objects;

public class LogAnalyzerServiceImpl implements LogAnalyzerService{


    public LogAnalyzerServiceImpl() {
    }

    @Override
    public void analyze(API api, Integer responseTime){
        Metadata metadata = api.getMetadata();
        if (Objects.isNull(metadata)){
            metadata = new Metadata();
        }
        updateFrequency(metadata);
        updateMaxTime(metadata,responseTime);
        updateMinTime(metadata,responseTime);
        updateSumOfTime(metadata,responseTime);
        api.setMetadata(metadata);
    }

    private void updateFrequency(Metadata metadata){
        metadata.setFrequency(1+metadata.getFrequency());
    }

    private void updateMaxTime(Metadata metadata,Integer responseTime){
        if (metadata.getMaxTIme()<responseTime) metadata.setMaxTIme(responseTime);
    }

    private void updateMinTime(Metadata metadata,Integer responseTime){
        if (metadata.getMinTime()>responseTime) metadata.setMinTime(responseTime);
    }

    private void updateSumOfTime(Metadata metadata,Integer responseTime){
        metadata.setSumOfTime(responseTime+metadata.getSumOfTime());
    }




}
