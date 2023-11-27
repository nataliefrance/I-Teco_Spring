package ru.shipova.ITeco_Spring.service;

import org.springframework.context.annotation.Lazy;
import ru.shipova.ITeco_Spring.model.PersonalInfo;


public interface PersonalInformationService {
    PersonalInfo getPersonalInfoById (Integer id);
}
