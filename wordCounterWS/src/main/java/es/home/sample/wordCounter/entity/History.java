package es.home.sample.wordCounter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class History extends GenericEntity<Integer> {

    private static final long serialVersionUID = 1L;
    private String freqWord;
    private Double apSize;
    private Double appTime;
    private Long tpTime;

    public History() {
    }

    public History(final Integer id, final String freqWord, final Double apSize, final Double appTime, final Long tpTime) {
        super();
        this.id = id;
        this.freqWord = freqWord;
        this.apSize = apSize;
        this.appTime = appTime;
        this.tpTime = tpTime;
    }

    @Override
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(final Integer id) {
        this.id = id;
    }

    @Column(name = "freq_word", length = 255)
    public String getFreqWord() {
        return freqWord;
    }

    public void setFreqWord(final String freqWord) {
        this.freqWord = freqWord;
    }

    @Column(name = "apSize", precision = 10, scale = 2)
    public Double getApSize() {
        return apSize;
    }

    public void setApSize(final Double apSize) {
        this.apSize = apSize;
    }

    @Column(name = "appTime", precision = 10, scale = 2)
    public Double getAppTime() {
        return appTime;
    }

    public void setAppTime(final Double appTime) {
        this.appTime = appTime;
    }

    @Column(name = "tpTime", precision = 10)
    public Long getTpTime() {
        return tpTime;
    }

    public void setTpTime(final Long tpTime) {
        this.tpTime = tpTime;
    }

}