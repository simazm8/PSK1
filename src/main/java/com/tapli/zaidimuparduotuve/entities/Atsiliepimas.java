package com.tapli.zaidimuparduotuve.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Atsiliepimas.findAll", query = "select a from Atsiliepimas as a")
})
@Table(name = "Atsiliepimas")
@Getter @Setter
public class Atsiliepimas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "atsiliepimai")
    private List<Zaidimas> zaidimai = new ArrayList<>();

    @Column(name = "APRASYMAS")
    private String aprasymas;




}
