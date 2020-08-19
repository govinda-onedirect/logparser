import fileProcessor.FileReading;
import fileProcessor.FileReadingImpl;
import logService.LogStreamProcessor;
import logService.LogStreamProcessorImpl;

public class MainExecutor {
    public static void main(String[] args) {
        String fileUrl = "/Users/govinda/Desktop/Sample input - Log Parser.csv";
        FileReading fileReading = new FileReadingImpl();
        LogStreamProcessor logStreamProcessor = new LogStreamProcessorImpl(5);
        fileReading.readAndProcess(fileUrl,logStreamProcessor);
        System.out.println(logStreamProcessor.getTopNElements(5));
        System.out.println(logStreamProcessor.getAnalytics());
        System.out.println(logStreamProcessor.getTopNElements());

    }
}
