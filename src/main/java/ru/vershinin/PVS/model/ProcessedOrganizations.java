package ru.vershinin.PVS.model;

import lombok.*;

import javax.persistence.*;

/**
 * ProcessedOrganizations
 *
 * @author Вершинин Пётр
 */
@Data
@Entity
public class ProcessedOrganizations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberOrganization;
    private String nameOrganization;
    private String inn;
    @Column(columnDefinition="text")
    private String adressMigrate;
    private String countPep;
    private String description;
    private String feedType;
    private  String signal;
    private String forecast;

    public ProcessedOrganizations() {
    }

    public ProcessedOrganizations(String numberOrganization, String nameOrganization, String inn, String adressMigrate, String countPep, String description, String feedType, String signal, String forecast) {
        this.numberOrganization = numberOrganization;
        this.nameOrganization = nameOrganization;
        this.inn = inn;
        this.adressMigrate = adressMigrate;
        this.countPep = countPep;
        this.description = description;
        this.feedType = feedType;
        this.signal = signal;
        this.forecast = forecast;
    }

}
