package com.tapli.zaidimuparduotuve.persistence;

import com.tapli.zaidimuparduotuve.entities.Zaidimas;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@ApplicationScoped
public class ZaidimaiDAO {

    @Inject
    private EntityManager em;

    public void persist(Zaidimas zaidimas) { this.em.persist(zaidimas);}

    public Zaidimas findOne(Integer id) {return em.find(Zaidimas.class, id); }

    public Zaidimas update(Zaidimas zaidimas) {return em.merge(zaidimas);}

}