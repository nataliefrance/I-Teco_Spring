package ru.shipova.ITeco_Spring.service;

import ru.shipova.ITeco_Spring.model.AccountInfo;

public interface AccountService {
    AccountInfo getAccountInfoById(Integer id);
    //метод, чтобы посмотреть инициализацию бина с @lazy
    String getPersonalInfoClass();
}
