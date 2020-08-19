package dtos;

public class Metadata {
    private int frequency;
    private int minTime;
    private int maxTIme;
    private long sumOfTime;

    public Metadata() {
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getMaxTIme() {
        return maxTIme;
    }

    public void setMaxTIme(int maxTIme) {
        this.maxTIme = maxTIme;
    }

    public long getSumOfTime() {
        return sumOfTime;
    }

    public void setSumOfTime(long sumOfTime) {
        this.sumOfTime = sumOfTime;
    }
}
