package com.tapli.zaidimuparduotuve.persistence;


import com.tapli.zaidimuparduotuve.entities.Atsiliepimas;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class AtsiliepimaiDAO {

    @Inject
    private EntityManager em;

    public void persist(Atsiliepimas atsiliepimas){
        this.em.persist(atsiliepimas);
    }

    public Atsiliepimas findOne(Integer id){
        return em.find(Atsiliepimas.class, id);
    }

    public Atsiliepimas update(Atsiliepimas atsiliepimas){
        return em.merge(atsiliepimas);
    }

    public Atsiliepimas findOneByName(String atsiliepimoAprasymas)
    {

        return em.createQuery("SELECT a FROM Atsiliepimas as a WHERE a.aprasymas = :atsiliepimoAprasymas", Atsiliepimas.class)
                .setParameter("atsiliepimoAprasymas", atsiliepimoAprasymas)
                .getResultList().stream().findFirst().orElse(null);
    }
}
