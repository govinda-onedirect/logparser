package fileProcessor;

import logService.LogStreamProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.stream.Stream;

public class FileReadingImpl implements FileReading {

    @Override
    public void readAndProcess(String path, LogStreamProcessor logStreamProcessor) {
        InputStreamReader logFileInputStream = null;
        BufferedReader logBufferReader = null;
        try {
             logFileInputStream = getInputStream(path);
            if (Objects.nonNull(logFileInputStream)) {
                logBufferReader = new BufferedReader(logFileInputStream);
                processLog(logBufferReader,logStreamProcessor);
            }
        }catch (FileNotFoundException e){
            System.out.println("The given is not present! path: "+path+" error: "+e.getMessage());
        } catch (IOException e) {
            System.out.println("Error occurred while reading the file! path: "+path+" error: "+e.getMessage());
        } finally {
            if (Objects.nonNull(logFileInputStream)) {
                try {
                    logFileInputStream.close();
                } catch (IOException e) {
                    System.out.println("Error occurred while close fileInput stream! "+e.getMessage());
                }
            }

            if (Objects.nonNull(logBufferReader)) {
                try {
                    logBufferReader.close();
                } catch (IOException e) {
                    System.out.println("Error occurred while close bufferReader! "+e.getMessage());
                }
            }
        }
    }



    private void processLog(BufferedReader logBufferReader, LogStreamProcessor logStreamProcessor) throws IOException {
        String line;
        String header = logBufferReader.readLine();
        while ((line=logBufferReader.readLine()) !=null){
            logStreamProcessor.processLog(line,header);
        }
    }




    private static FileReader getInputStream(String path) throws FileNotFoundException{
        File logFile = new File(path);
        return new FileReader(logFile);
    }
}
