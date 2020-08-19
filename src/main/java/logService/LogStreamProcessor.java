package logService;

import dtos.API;

import java.util.List;
import java.util.stream.Stream;

public interface LogStreamProcessor {

    public void processLog(String logStream,String header);

    public List<String> getTopNElements(int n);

    List<String> getTopNElements();

    public List<String> getAnalytics();
}
