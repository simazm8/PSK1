package com.tapli.zaidimuparduotuve.usecases;

import com.tapli.zaidimuparduotuve.interceptors.LoggedInvocation;
import com.tapli.zaidimuparduotuve.services.SerialNumberGenerator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RequestScoped
@Named
public class GenerateZaidimoSerialNumber implements Serializable {

    @Inject
    SerialNumberGenerator serialNumberGenerator;

    public GenerateZaidimoSerialNumber(){

    }

    private CompletableFuture<Integer> serialNumberGenerationTask = null;

    @LoggedInvocation
    public void generateNewSerialNumber(){
        serialNumberGenerationTask = CompletableFuture.supplyAsync(() -> serialNumberGenerator.generateSerialNumber());
    }

    public boolean isSerialNumberGenerationRunning() {
        return serialNumberGenerationTask != null && !serialNumberGenerationTask.isDone();
    }

    public String getSerialNumberGenerationStatus() throws ExecutionException, InterruptedException {
        if (serialNumberGenerationTask == null){
            return null;
        }
        else if (isSerialNumberGenerationRunning()){
            return "Generuojamas serijinis numeris";
        }
        return "Siūlomas serijinis numeris: " + serialNumberGenerationTask.get();
    }
}
