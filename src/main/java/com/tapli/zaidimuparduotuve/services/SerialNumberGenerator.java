package com.tapli.zaidimuparduotuve.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class SerialNumberGenerator implements Serializable {

    public Integer generateSerialNumber(){
        int serialNumber;
        Random random = new Random();
        char[] digits = new char[8];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < 8; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        serialNumber = Integer.parseInt(new String(digits));
        return (int)serialNumber;
    }
}
