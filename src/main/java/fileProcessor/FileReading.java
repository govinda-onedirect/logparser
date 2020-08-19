package fileProcessor;


import logService.LogStreamProcessor;

public interface FileReading {
    void readAndProcess(String path, LogStreamProcessor logStreamProcessor);
}
