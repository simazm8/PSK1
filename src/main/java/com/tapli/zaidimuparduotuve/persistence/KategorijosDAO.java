package com.tapli.zaidimuparduotuve.persistence;

import com.tapli.zaidimuparduotuve.entities.Kategorija;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class KategorijosDAO {

    @Inject
    private EntityManager em;

    public List<Kategorija> loadAll() {return em.createNamedQuery("Kategorija.findAll", Kategorija.class).getResultList(); }

    public void setEm(EntityManager em) {this.em = em; }

    public void persist(Kategorija kategorija) {this.em.persist(kategorija);}

    public Kategorija findOne(Integer id) {return em.find(Kategorija.class, id);};

}