package dtos;

public enum Elements {
    timestamp,url,method,response_time,response_code;

    public static Elements getElements(String ele){
        for(Elements e:Elements.values()){
            if (e.name().equals(ele)){
                return e;
            }
        }
        return null;
    }
}
