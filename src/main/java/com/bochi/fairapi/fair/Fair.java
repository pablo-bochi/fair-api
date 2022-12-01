package com.bochi.fairapi.fair;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@AllArgsConstructor
@Builder(toBuilder = true)
public class Fair {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "long")
    private String lon;

    @Column(name = "lat")
    private String lat;

    @Column(name = "setcens")
    private String censusSector;

    @Column(name = "areap")
    private String wightedArea;

    @Column(name = "coddist")
    private String districtCode;

    @Column(name = "distrito")
    private String district;

    @Column(name = "codsubpref")
    private String subPrefectureCode;

    @Column(name = "subpref")
    private String subPrefecture;

    @Column(name = "regiao5")
    private String region5;

    @Column(name = "regiao8")
    private String region8;

    @Column(name = "nome_feira")
    private String fairName;

    @Column(name = "registro", nullable = false)
    private String registerCode;

    @Column(name = "logradouro")
    private String address;

    @Column(name = "numero")
    private String number;

    @Column(name = "bairro")
    private String neighbourhood;

    @Column(name = "referencia")
    private String refPoint;

}
