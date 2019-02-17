package ua.kiev.prog.prohw4.bank.dao;

import ua.kiev.prog.prohw4.bank.EMFProvider;
import ua.kiev.prog.prohw4.bank.entity.Account;
import ua.kiev.prog.prohw4.bank.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AccountDaoImpl implements AccountDao {
    private static final AccountDao instance = new AccountDaoImpl();
    private EntityManagerFactory emf = EMFProvider.getInstance().getEmf();

    private AccountDaoImpl() {
    }

    public static AccountDao getInstance() {
        return instance;
    }

    @Override
    public Account getAccount(Integer id) {
        EntityManager em = emf.createEntityManager();
        Account account = em.createQuery("SELECT a FROM Account a LEFT JOIN FETCH a.transactions WHERE a.id = " + id, Account.class).getSingleResult();
        em.close();
        return account;
    }

    @Override
    public synchronized void updateAccount(Account account, Transaction transaction) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            account.addTransaction(transaction);
            em.merge(account);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public synchronized void updateAccount(Account fromAcc, Account toAcc, Transaction transaction) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(transaction);
            fromAcc.addTransaction(transaction);
            toAcc.addTransaction(transaction);
            em.merge(fromAcc);
            em.merge(toAcc);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
