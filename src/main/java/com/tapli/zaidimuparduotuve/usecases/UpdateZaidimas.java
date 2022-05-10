package com.tapli.zaidimuparduotuve.usecases;

import com.tapli.zaidimuparduotuve.entities.Zaidimas;
import com.tapli.zaidimuparduotuve.interceptors.LoggedInvocation;
import com.tapli.zaidimuparduotuve.persistence.ZaidimaiDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateZaidimas implements Serializable {

    private Zaidimas zaidimas;

    @Inject
    private ZaidimaiDAO zaidimaiDAO;

    @PostConstruct
    private void init(){
        Map<String,String > requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer zaidimoId = Integer.parseInt(requestParameters.get("zaidimoId"));
        this.zaidimas = zaidimaiDAO.findOne(zaidimoId);
    }

    @Transactional
    @LoggedInvocation
    public String updateZaidimasName(){
        try {
            zaidimaiDAO.update(this.zaidimas);
        }
        catch (OptimisticLockException e){
             return "/zaidimasDetails.xhtml?faces-redirect=true&zaidimoId=" + this.zaidimas.getId() + "&error=optimistic-lock-exception";
        }
        return "zaidimai.xhtml?kategorijosId=" + this.zaidimas.getKategorija().getId() + "&faces-redirect=true";
    }
}
