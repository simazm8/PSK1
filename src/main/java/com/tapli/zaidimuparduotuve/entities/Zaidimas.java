package com.tapli.zaidimuparduotuve.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Zaidimas.findAll", query = "select i from Zaidimas as i")
})
@Table(name = "Zaidimas")
@Getter
@Setter
public class Zaidimas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PAVADINIMAS")
    private String pavadinimas;

    @Column(name = "SERIJINIS_NR")
    private Integer serijinisNr;

    @Column(name = "KAINA")
    private Double kaina;

    @ManyToOne
    @JoinColumn(name = "KATEGORIJA_ID")
    private Kategorija kategorija;

    @ManyToMany
    @JoinTable(name = "ZAIDIMO_ATSILIEPIMAI")
    private List<Atsiliepimas> atsiliepimai = new ArrayList<>();

    public void addAtsiliepimas(Atsiliepimas atsiliepimas){
        this.atsiliepimai.add(atsiliepimas);
    }

    public Zaidimas() {

    }

    @Override
    public int hashCode() {
        return Objects.hash(pavadinimas);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Zaidimas zaidimas = (Zaidimas) obj;
        return Objects.equals(pavadinimas, zaidimas.pavadinimas);
    }
}
