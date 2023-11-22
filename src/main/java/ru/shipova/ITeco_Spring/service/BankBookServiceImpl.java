package ru.shipova.ITeco_Spring.service;

import org.springframework.stereotype.Service;
import ru.shipova.ITeco_Spring.model.BankBook;
import ru.shipova.ITeco_Spring.model.annotation.InjectRandom;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankBookServiceImpl implements BankBookService{

    @InjectRandom(max = 100)
    private Integer number;

    @Override
    public List<BankBook> getBankBookById(Integer id) {
        BankBook bankBook = new BankBook();
        bankBook.setNumber(number.longValue());
        bankBook.setUserId(id);
        ArrayList<BankBook> bankBooks = new ArrayList<BankBook>();
        bankBooks.add(bankBook);
        return bankBooks;
    }
}
