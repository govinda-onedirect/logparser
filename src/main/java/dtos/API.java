package dtos;

public class API implements Comparable{
    private String method;
    private String maskedUrl;
    private Metadata metadata;
    private Integer responseTime;



    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMaskedUrl() {
        return maskedUrl;
    }

    public void setMaskedUrl(String maskedUrl) {
        this.maskedUrl = maskedUrl;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }


    public String getAPIKey(){
        return method + "_" + maskedUrl;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        API api = (API) o;

        if (!method.equals(api.method)) return false;
        return maskedUrl.equals(api.maskedUrl);
    }

    @Override
    public int hashCode() {
        int result = method.hashCode();
        result = 31 * result + maskedUrl.hashCode();
        return result;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }


    @Override
    public int compareTo(Object o) {
        return ((API) o).getMetadata().getFrequency()-
         this.getMetadata().getFrequency();
    }
}
