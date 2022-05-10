package com.tapli.zaidimuparduotuve.usecases;

import com.tapli.zaidimuparduotuve.mybatis.dao.ZaidimasMapper;
import com.tapli.zaidimuparduotuve.mybatis.dao.KategorijaMapper;
import com.tapli.zaidimuparduotuve.mybatis.model.Zaidimas;
import com.tapli.zaidimuparduotuve.mybatis.model.Kategorija;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
public class ZaidimoMyBatis {

    @Inject
    private ZaidimasMapper zaidimasMapper;

    @Inject
    private KategorijaMapper kategorijaMapper;

    @Getter @Setter
    private Zaidimas zaidimasToCreate = new Zaidimas();

    @Transactional
    public String createZaidimas(String kategorijaName){
        Kategorija kategorija = this.kategorijaMapper.selectOneByName(kategorijaName);
        zaidimasToCreate.setKategorijaId(kategorija.getId());
        this.zaidimasMapper.insert(zaidimasToCreate);
        return "/myBatis/zaidimai?faces-redirect=true";
    }
}
