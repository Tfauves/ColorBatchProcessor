package com.bizboar.superCoolBatchProgram.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "RAL_Classic_Colors" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RALClassicColors {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "RAL")
    private String ral;

    @Transient
    private String rgb;

    @Column(name = "HEX")
    private String hex;

    @Transient
    private String cmyk;

   @Transient
    private Double lrv;

    @Column(name = "LRV")
    private String lrvCategory;

    @Column(name = "English")
    private String english;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRal() {
        return ral;
    }

    public void setRal(String ral) {
        this.ral = ral;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public String getCmyk() {
        return cmyk;
    }

    public void setCmyk(String cmyk) {
        this.cmyk = cmyk;
    }

    public Double getLrv() {
        return lrv;
    }

    public void setLrv(Double lrv) {
        this.lrv = lrv;
    }

    public String getLrvCategory() {
        return lrvCategory;
    }

    public void setLrvCategory(String lrvCategory) {
        this.lrvCategory = lrvCategory;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

}
