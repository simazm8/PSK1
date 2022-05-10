package com.tapli.zaidimuparduotuve.usecases;

import com.tapli.zaidimuparduotuve.entities.Zaidimas;
import com.tapli.zaidimuparduotuve.entities.Kategorija;
import com.tapli.zaidimuparduotuve.interceptors.LoggedInvocation;
import com.tapli.zaidimuparduotuve.persistence.ZaidimaiDAO;
import com.tapli.zaidimuparduotuve.persistence.KategorijosDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class ZaidimasForCategory implements Serializable {

    @Inject
    private KategorijosDAO kategorijosDAO;

    @Inject
    private ZaidimaiDAO zaidimaiDAO;

    @Getter @Setter
    private Kategorija kategorija;

    @Getter @Setter
    private Zaidimas zaidimasToCreate = new Zaidimas();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer zaidimoId = Integer.parseInt(requestParameters.get("kategorijosId"));
        this.kategorija = kategorijosDAO.findOne(zaidimoId);
    }

    @Transactional
    @LoggedInvocation
    public void createZaidimas() {
        zaidimasToCreate.setKategorija(this.kategorija);
        zaidimaiDAO.persist(zaidimasToCreate);
    }
}
