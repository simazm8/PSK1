package com.tapli.zaidimuparduotuve.usecases;

import com.tapli.zaidimuparduotuve.mybatis.dao.AtsiliepimasMapper;
import com.tapli.zaidimuparduotuve.mybatis.dao.ZaidimasMapper;
import com.tapli.zaidimuparduotuve.mybatis.dao.ZaidimoAtsiliepimasMapper;
import com.tapli.zaidimuparduotuve.mybatis.model.Atsiliepimas;
import com.tapli.zaidimuparduotuve.mybatis.model.Zaidimas;
import com.tapli.zaidimuparduotuve.mybatis.model.ZaidimoAtsiliepimas;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class AtsiliepimaiForZaidimasMyBatis {

    @Inject
    private ZaidimasMapper zaidimasMapper;

    @Inject
    private AtsiliepimasMapper aprasymasMapper;

    @Inject
    private ZaidimoAtsiliepimasMapper zaidimoAtsiliepimasMapper;
    
    @Getter @Setter
    private Zaidimas zaidimas;

    @Getter @Setter
    private Atsiliepimas atsiliepimasToCreate = new Atsiliepimas();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer zaidimoId = Integer.parseInt(requestParameters.get("zaidimoId"));
        this.zaidimas = zaidimasMapper.selectByPrimaryKey(zaidimoId);
    }

    @Transactional
    public void addAtsiliepimas(String atsiliepimoAprasymas){

        Atsiliepimas tempAtsiliepimas = aprasymasMapper.findOneByName(atsiliepimoAprasymas);
        if(tempAtsiliepimas == null){
            atsiliepimasToCreate.setAprasymas(atsiliepimoAprasymas);
            aprasymasMapper.insert(atsiliepimasToCreate);
            tempAtsiliepimas = atsiliepimasToCreate;
        }

        ZaidimoAtsiliepimas zaidimoAtsiliepimas = new ZaidimoAtsiliepimas();
        zaidimoAtsiliepimas.setAtsiliepimai_Id(tempAtsiliepimas.getId());
        zaidimoAtsiliepimas.setZaidimai_Id(this.zaidimas.getId());
        zaidimoAtsiliepimasMapper.insert(zaidimoAtsiliepimas);
    }

}
