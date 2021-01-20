package ru.vershinin.PVS.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * ProcessedOrganizations
 *
 * @author Вершинин Пётр
 */
@Entity
public class ProcessedOrganizations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberOrganization;
    private String nameOrganization;
    private String INN;
    private String adressMigrate;
    private String countPep;
    private String description;
    private String feedType;
    private  String signal;
    private String forecast;

    public ProcessedOrganizations() {
    }

    public ProcessedOrganizations(String numberOrganization, String nameOrganization, String INN, String adressMigrate, String countPep, String description, String feedType, String signal, String forecast) {
        this.numberOrganization = numberOrganization;
        this.nameOrganization = nameOrganization;
        this.INN = INN;
        this.adressMigrate = adressMigrate;
        this.countPep = countPep;
        this.description = description;
        this.feedType = feedType;
        this.signal = signal;
        this.forecast = forecast;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberOrganization() {
        return numberOrganization;
    }

    public void setNumberOrganization(String numberOrganization) {
        this.numberOrganization = numberOrganization;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }

    public String getINN() {
        return INN;
    }

    public void setINN(String INN) {
        this.INN = INN;
    }

    public String getAdressMigrate() {
        return adressMigrate;
    }

    public void setAdressMigrate(String adressMigrate) {
        this.adressMigrate = adressMigrate;
    }

    public String getCountPep() {
        return countPep;
    }

    public void setCountPep(String countPep) {
        this.countPep = countPep;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }
}
