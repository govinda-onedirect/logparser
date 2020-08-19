package logService;

import dtos.Elements;
import dtos.Method;

import java.util.Map;

public class LogValidatorService {

    public static boolean validateLog(String log, Map<Elements, Integer> header){
        if (log==null || log.length()==0){
            System.out.println("log data is empty!");
            return false;
        }
        String[] logElements = log.split(",");
        if (logElements.length <5){
            System.out.println("log data is inapproprate! " + log);
            return false;
        }
        if (!Method.getMethod(logElements[header.get(Elements.method)])){
            System.out.println("METHOD Type is not valid! "+ log);
            return false;
        }
        if (!parseResponseTime(logElements[header.get(Elements.response_time)])){
            System.out.println("Response time is not valid! "+ log);
            return false;
        }

        return true;
    }

    private static boolean parseResponseTime(String responseTime){
        try {
            Integer.parseInt(responseTime);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
