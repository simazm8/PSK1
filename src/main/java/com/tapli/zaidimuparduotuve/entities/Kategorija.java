package com.tapli.zaidimuparduotuve.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Kategorija.findAll", query = "select k from Kategorija as k")
})
@Table(name = "KATEGORIJA")
@Getter
@Setter
public class Kategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String pavadinimas;

    @OneToMany(mappedBy = "kategorija")
    private List<Zaidimas> zaidimai = new ArrayList<>();

    @Override
    public int hashCode() {
        return Objects.hash(pavadinimas);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Kategorija kategorija = (Kategorija) obj;
        return Objects.equals(pavadinimas, kategorija.pavadinimas);


    }
}