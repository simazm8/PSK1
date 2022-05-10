package com.tapli.zaidimuparduotuve.rest;

import com.tapli.zaidimuparduotuve.entities.Zaidimas;
import com.tapli.zaidimuparduotuve.persistence.ZaidimaiDAO;
import com.tapli.zaidimuparduotuve.rest.contracts.ZaidimasDto;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/zaidimai")
public class ZaidimaiController {

    @Inject
    @Getter
    @Setter
    private ZaidimaiDAO zaidimaiDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Zaidimas zaidimas = zaidimaiDAO.findOne(id);
        if (zaidimas == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ZaidimasDto zaidimasDto = new ZaidimasDto();
        zaidimasDto.setPavadinimas(zaidimas.getPavadinimas());
        zaidimasDto.setSerijinis_Nr(zaidimas.getSerijinisNr());
        zaidimasDto.setKategorija(zaidimas.getKategorija().getPavadinimas());

        return Response.ok(zaidimasDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer zaidimoId,
            ZaidimasDto zaidimasData) {
        try {
            Zaidimas existingZaidimas = zaidimaiDAO.findOne(zaidimoId);
            if (existingZaidimas == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingZaidimas.setPavadinimas(zaidimasData.getPavadinimas());
            existingZaidimas.setSerijinisNr(zaidimasData.getSerijinis_Nr());
            existingZaidimas.setKaina(zaidimasData.getKaina());
            zaidimaiDAO.update(existingZaidimas);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }

    }
}
