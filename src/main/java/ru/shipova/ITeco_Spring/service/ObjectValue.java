package ru.shipova.ITeco_Spring.service;

import org.springframework.stereotype.Component;
import ru.shipova.ITeco_Spring.model.annotation.EncryptResult;
import ru.shipova.ITeco_Spring.model.annotation.InjectRandom;

@Component
public class ObjectValue implements IObject{

    @InjectRandom
    private int value1;

    @InjectRandom(min = 10, max = 100)
    private int value2;

    @EncryptResult
    public String getInfo(){
        return "ObjectValue{" +
                "value1 = " + value1 +
                ", value2 = " + value2 +
                "}";
    }
}
