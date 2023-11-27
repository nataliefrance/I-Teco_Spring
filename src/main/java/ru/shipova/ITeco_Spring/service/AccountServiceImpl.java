package ru.shipova.ITeco_Spring.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.shipova.ITeco_Spring.model.AccountInfo;
import ru.shipova.ITeco_Spring.model.BankBook;
import ru.shipova.ITeco_Spring.model.PersonalInfo;

import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService{

    private final PersonalInformationService personalInformationService;
    private final Map<String, BankBookService> bankBookServices;

    public AccountServiceImpl(@Lazy PersonalInformationService personalInformationService,
                              Map<String, BankBookService> bankBookServices) {
        this.personalInformationService = personalInformationService;
        this.bankBookServices = bankBookServices;
    }

    @Override
    public AccountInfo getAccountInfoById(Integer id) {
        AccountInfo accountInfo = new AccountInfo();
        PersonalInfo personalInfo = personalInformationService.getPersonalInfoById(id);
        accountInfo.setPersonalInfo(personalInfo);
        System.out.println(bankBookServices);

        for (Map.Entry<String, BankBookService> bankBookServiceEntry : bankBookServices.entrySet()) {
            BankBookService bankBookService = bankBookServiceEntry.getValue();
            List<BankBook> bankBooks = bankBookService.getBankBookById(id);
            System.out.println(bankBooks);
            if (!bankBooks.isEmpty()) {
                accountInfo.setBankBooks(bankBooks);
            }
        }
        return accountInfo;
    }

    @Override
    public String getPersonalInfoClass() {
        //метод показывает, что меняется в зависимости от @lazy
        return personalInformationService.getClass().toString();
    }
}
