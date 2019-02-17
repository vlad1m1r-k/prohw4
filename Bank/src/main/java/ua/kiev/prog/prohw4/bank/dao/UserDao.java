package ua.kiev.prog.prohw4.bank.dao;

import ua.kiev.prog.prohw4.bank.entity.Account;
import ua.kiev.prog.prohw4.bank.entity.User;

import java.util.List;

public interface UserDao {
    List<User> gerUsers();
    User getUser(Integer id);

    void addUser(User user);
    void updateUser(User user, Account account);
}
