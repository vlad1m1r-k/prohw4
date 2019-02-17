package ua.kiev.prog.prohw4.bank;

import ua.kiev.prog.prohw4.bank.dao.AccountDao;
import ua.kiev.prog.prohw4.bank.dao.AccountDaoImpl;
import ua.kiev.prog.prohw4.bank.dao.CurrencyDao;
import ua.kiev.prog.prohw4.bank.dao.CurrencyDaoImpl;
import ua.kiev.prog.prohw4.bank.dao.UserDao;
import ua.kiev.prog.prohw4.bank.dao.UserDaoImpl;
import ua.kiev.prog.prohw4.bank.entity.Account;
import ua.kiev.prog.prohw4.bank.entity.Transaction;
import ua.kiev.prog.prohw4.bank.entity.User;

import java.util.Date;
import java.util.List;

public class FinanceOperations {
    private static final FinanceOperations instance = new FinanceOperations();
    private AccountDao accountDao = AccountDaoImpl.getInstance();
    private CurrencyDao currencyDao = CurrencyDaoImpl.getInstance();
    private UserDao userDao = UserDaoImpl.getInstance();

    private FinanceOperations() {
    }

    public static FinanceOperations getInstance() {
        return instance;
    }

    public synchronized void addFunds(Integer accountId, Integer amount) {
        Account account = accountDao.getAccount(accountId);
        account.addFunds(amount);
        Transaction transaction = new Transaction(new Date(), null, account, 0, amount);
        accountDao.updateAccount(account, transaction);
    }

    public synchronized void transferFunds(Integer from, Integer to, Integer amount) {
        if (!from.equals(to)) {
            Account fromAcc = accountDao.getAccount(from);
            Account toAcc = accountDao.getAccount(to);
            if (fromAcc.getType() == toAcc.getType()) {
                if (fromAcc.decreaseFunds(amount)) {
                    toAcc.addFunds(amount);
                    Transaction transaction = new Transaction(new Date(), fromAcc, toAcc, amount, amount);
                    accountDao.updateAccount(fromAcc, toAcc, transaction);
                }
            } else {
                if (fromAcc.decreaseFunds(amount)) {
                    Integer convertedAmount = convert(fromAcc.getType(), toAcc.getType(), amount);
                    toAcc.addFunds(convertedAmount);
                    Transaction transaction = new Transaction(new Date(), fromAcc, toAcc, amount, convertedAmount);
                    accountDao.updateAccount(fromAcc, toAcc, transaction);
                }
            }
        }
    }

    public Integer overallBalance(Integer userId) {
        User user = userDao.getUser(userId);
        List<Account> accounts = user.getAccounts();
        Integer balance = 0;
        for (Account account : accounts) {
            if (account.getType() == Currency.UAH) {
                balance += account.getAmount();
            } else {
                balance += convert(account.getType(), Currency.UAH, account.getAmount());
            }
        }
        return balance;
    }

    private Integer convert(Currency from, Currency to, Integer amount) {
        Float fromRate = from == Currency.UAH ? 100L : (float) currencyDao.getRate(from);
        Float toRate = to == Currency.UAH ? 100L : (float) currencyDao.getRate(to);
        return (int)(amount * (fromRate / toRate));
    }
}
