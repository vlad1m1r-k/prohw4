package ua.kiev.prog.prohw4.bank.entity;

import ua.kiev.prog.prohw4.bank.Currency;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency type;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "account_transactions",
    joinColumns = {
            @JoinColumn(name = "account", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "transaction", referencedColumnName = "id")})
    private List<Transaction> transactions = new ArrayList<>();

    public Account() {
    }

    public Account(Currency type, User user, Integer amount) {
        this.type = type;
        this.user = user;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public Currency getType() {
        return type;
    }

    public Integer getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void addFunds(Integer amount) {
        this.amount += amount;
    }

    public boolean decreaseFunds(Integer amount) {
        if (this.amount - amount >= 0) {
            this.amount -= amount;
            return true;
        }
        return false;
    }
}
