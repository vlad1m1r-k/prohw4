package ua.kiev.prog.prohw4.bank.dao;

import ua.kiev.prog.prohw4.bank.entity.Account;
import ua.kiev.prog.prohw4.bank.entity.Transaction;

public interface AccountDao {
    Account getAccount(Integer id);

    void updateAccount(Account account, Transaction transaction);
    void updateAccount(Account fromAcc, Account toAcc, Transaction transaction);
}
