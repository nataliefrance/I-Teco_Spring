package ru.shipova.ITeco_Spring.service;

import ru.shipova.ITeco_Spring.model.PersonalInfo;

public class PersonalInformationServiceImpl implements PersonalInformationService{
    @Override
    public PersonalInfo getPersonalInfoById(Integer id) {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName("NAME");
        personalInfo.setUserId(id);
        return personalInfo;
    }
}
