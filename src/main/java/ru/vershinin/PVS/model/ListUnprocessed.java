package ru.vershinin.PVS.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * ListUnprocessed
 *
 * @author Вершинин Пётр
 */
@Data
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

}
