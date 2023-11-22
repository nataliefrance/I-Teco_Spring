package ru.shipova.ITeco_Spring.service;

import ru.shipova.ITeco_Spring.model.BankBook;

import java.util.List;

public interface BankBookService {
    List<BankBook> getBankBookById (Integer id);
}
