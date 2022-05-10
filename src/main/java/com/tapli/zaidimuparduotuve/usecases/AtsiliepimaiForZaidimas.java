package com.tapli.zaidimuparduotuve.usecases;

import com.tapli.zaidimuparduotuve.entities.Atsiliepimas;
import com.tapli.zaidimuparduotuve.entities.Zaidimas;
import com.tapli.zaidimuparduotuve.persistence.AtsiliepimaiDAO;
import com.tapli.zaidimuparduotuve.persistence.ZaidimaiDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class AtsiliepimaiForZaidimas {

    @Inject
    private ZaidimaiDAO zaidimaiDAO;

    @Inject
    private AtsiliepimaiDAO atsiliepimaiDAO;

    @Getter @Setter
    private Zaidimas zaidimas;

    @Getter @Setter
    private Atsiliepimas atsiliepimasToCreate = new Atsiliepimas();

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer zaidimoId = Integer.parseInt(requestParameters.get("zaidimoId"));
        this.zaidimas = zaidimaiDAO.findOne(zaidimoId);
    }

    @Transactional
    public void addAtsiliepimas(String atsiliepimoAprasymas){
        Atsiliepimas tempAtsiliepimas = atsiliepimaiDAO.findOneByName(atsiliepimoAprasymas);

        if(tempAtsiliepimas == null){
            atsiliepimasToCreate.setAprasymas(atsiliepimoAprasymas);
            atsiliepimaiDAO.persist(atsiliepimasToCreate);
            tempAtsiliepimas = atsiliepimasToCreate;
        }
        zaidimas.addAtsiliepimas(tempAtsiliepimas);
    }

}
