package ru.shipova.ITeco_Spring.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.shipova.ITeco_Spring.model.PersonalInfo;

@Service
public class PersonalInformationServiceImpl implements PersonalInformationService{

    @Value("${name}")
    private String name;

    public PersonalInformationServiceImpl() {
    }

    @PostConstruct
    //на этапе этого метода bean не до конца сконфигурирован, ещё нет proxy.
    //Поэтому здесь лучше не использовать бизнес логику
    public void init() {
        if (name.contains("N")) {
            System.out.println("Contains 'N'");
        }
    }

    @Override
    public PersonalInfo getPersonalInfoById(Integer id) {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName(name);
        personalInfo.setUserId(id);
        return personalInfo;
    }
}
