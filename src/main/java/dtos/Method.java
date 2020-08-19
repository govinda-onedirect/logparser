package dtos;

public enum Method {
    GET,POST,PUT,OPTION,HEAD;

    Method() {
    }

    public static boolean getMethod(String method){
        for(Method method1 :Method.values()){
            if (method1.name().equals(method)){
                return true;
            }
        }
        return false;
    }
}
