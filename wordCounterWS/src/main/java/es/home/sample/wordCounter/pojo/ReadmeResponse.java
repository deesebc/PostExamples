package es.home.sample.wordCounter.pojo;

import java.io.Serializable;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReadmeResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String type;
    private Integer amount;
    private Integer number;
    @JsonProperty(value = "number_max")
    private Integer numberMax;
    private String format;
    // @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime time;
    @JsonProperty(value = "text_out")
    private String textOut;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(final Integer amount) {
        this.amount = amount;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(final Integer number) {
        this.number = number;
    }

    public Integer getNumberMax() {
        return numberMax;
    }

    public void setNumberMax(final Integer numberMax) {
        this.numberMax = numberMax;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(final LocalTime time) {
        this.time = time;
    }

    public String getTextOut() {
        return textOut;
    }

    public void setTextOut(final String textOut) {
        this.textOut = textOut;
    }

}
