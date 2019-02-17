package ua.kiev.prog.prohw4.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "from_account")
    private Account from;

    @ManyToOne
    @JoinColumn(name = "to_account")
    private Account to;

    @Column(nullable = false)
    private Integer subtracted;

    @Column(nullable = false)
    private Integer appended;

    public Transaction() {
    }

    public Transaction(Date date, Account from, Account to, Integer subtracted, Integer appended) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.subtracted = subtracted;
        this.appended = appended;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Account getFrom() {
        return from;
    }

    public Account getTo() {
        return to;
    }

    public Integer getSubtracted() {
        return subtracted;
    }

    public Integer getAppended() {
        return appended;
    }
}
