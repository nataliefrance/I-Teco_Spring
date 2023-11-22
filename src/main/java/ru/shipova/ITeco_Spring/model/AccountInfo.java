package ru.shipova.ITeco_Spring.model;

import lombok.Data;

import java.util.List;

@Data
public class AccountInfo {
    private PersonalInfo personalInfo;
    private List<BankBook> bankBooks;
}
