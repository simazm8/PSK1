package com.tapli.zaidimuparduotuve.usecases;

import com.tapli.zaidimuparduotuve.mybatis.dao.KategorijaMapper;
import com.tapli.zaidimuparduotuve.mybatis.model.Kategorija;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class KategorijosMyBatis {

    @Inject
    private KategorijaMapper kategorijaMapper;

    @Getter
    private List<Kategorija> allKategorijos;

    @Getter @Setter
    private Kategorija kategorijaToCreate = new Kategorija();

    @PostConstruct
    public void init() {
        this.loadAllKategorijos();
    }

    private void loadAllKategorijos() {

        this.allKategorijos = kategorijaMapper.selectAll();
    }

    @Transactional
    public String createKategorija(){
        kategorijaMapper.insert(kategorijaToCreate);
        return "/myBatis/kategorijos?faces-redirect=true";
    }
}
