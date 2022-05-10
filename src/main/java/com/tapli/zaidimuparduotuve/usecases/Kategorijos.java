package com.tapli.zaidimuparduotuve.usecases;

import com.tapli.zaidimuparduotuve.entities.Kategorija;
import com.tapli.zaidimuparduotuve.persistence.KategorijosDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Kategorijos {

    @Inject
    private KategorijosDAO kategorijosDAO;

    @Getter @Setter
    private Kategorija kategorijaToCreate = new Kategorija();

    @Getter
    private List<Kategorija> allKategorijos;

    @PostConstruct
    public void init(){
        loadAllKategorijos();
    }
    @Transactional
    public void createKategorija() {
        this.kategorijosDAO.persist(kategorijaToCreate);
    }

    private void loadAllKategorijos() {
        this.allKategorijos = kategorijosDAO.loadAll();
    }

}
