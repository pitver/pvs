package ru.vershinin.PVS.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ListUnprocessed
 *
 * @author Вершинин Пётр
 */
@Entity
public class ListUnprocessed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fio;
    private String adress;
    private String receivingParty;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime targetDate;
    private String payer;

    public ListUnprocessed() {
    }

    public ListUnprocessed(String fio, String adress, String receivingParty, LocalDateTime targetDate, String payer) {
        this.fio = fio;
        this.adress = adress;
        this.receivingParty = receivingParty;
        this.targetDate = targetDate;
        this.payer = payer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getReceivingParty() {
        return receivingParty;
    }

    public void setReceivingParty(String receivingParty) {
        this.receivingParty = receivingParty;
    }

    public LocalDateTime getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDateTime targetDate) {
        this.targetDate = targetDate;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }
}
