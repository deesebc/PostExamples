package es.home.sample.wordCounter.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextResponse {

    @JsonProperty(value = "freqWord")
    private String freqWord;
    @JsonProperty(value = "apSize")
    private Double apSize;
    @JsonProperty(value = "appTime")
    private Double appTime;
    @JsonProperty(value = "tpTime")
    private Long tpTime;

    public String getFreqWord() {
        return freqWord;
    }

    public void setFreqWord(final String freqWord) {
        this.freqWord = freqWord;
    }

    public Double getApSize() {
        return apSize;
    }

    public void setApSize(final Double apSize) {
        this.apSize = apSize;
    }

    public Double getAppTime() {
        return appTime;
    }

    public void setAppTime(final Double d) {
        appTime = d;
    }

    public Long getTpTime() {
        return tpTime;
    }

    public void setTpTime(final Long tpTime) {
        this.tpTime = tpTime;
    }

}
