package ru.vershinin.PVS.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@lombok.Data
@Entity
public class ListOrg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String num;
    private String nameOrg;
    private String inn;

    public ListOrg() {
    }

    public ListOrg(String num, String nameOrg, String inn) {
        this.num = num;
        this.nameOrg = nameOrg;
        this.inn = inn;
    }

}
