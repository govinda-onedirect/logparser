package logService;


public class LogMaskingServiceImpl implements LogMaskingService{

    private static final String DIVIDE = "/";
    private static final String ID_PATH_VARIABLE_PART_1 = "{id";
    private static final String ID_PATH_VARIABLE_PART_2 = "}";


    @Override
    public String maskAllIntegersFromUrl(String url){
        String[] dividedComponent =  url.split(DIVIDE);
        if(dividedComponent.length==0) return url;
        int count= 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1, dividedComponentLength = dividedComponent.length; i < dividedComponentLength; i++) {
            String component = dividedComponent[i];
            sb.append(DIVIDE);
            if (checkIntegerComponent(component)) {
                sb.append(ID_PATH_VARIABLE_PART_1);
                sb.append(count++);
                sb.append(ID_PATH_VARIABLE_PART_2);
            } else sb.append(component);
        }

        return sb.toString();

    }

    private boolean checkIntegerComponent(String component){
        try {
            Integer.parseInt(component);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

}
