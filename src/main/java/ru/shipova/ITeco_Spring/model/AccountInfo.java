package ru.shipova.ITeco_Spring.model;

import lombok.Data;

import java.util.List;
/*
@Data – это сокращенная аннотация,
сочетающая возможности @ToString, @EqualsAndHashCode, @Getter @Setter и @RequiredArgsConstructor.
Генерирует весь шаблонный код, вовлеченный в работу с объектами POJO (Plain Old Java Objects).*/

@Data
public class AccountInfo {
    private PersonalInfo personalInfo;
    private List<BankBook> bankBooks;
}
