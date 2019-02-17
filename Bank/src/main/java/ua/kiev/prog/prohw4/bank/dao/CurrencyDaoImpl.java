package ua.kiev.prog.prohw4.bank.dao;

import ua.kiev.prog.prohw4.bank.Currency;
import ua.kiev.prog.prohw4.bank.EMFProvider;
import ua.kiev.prog.prohw4.bank.entity.CurrencyRate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CurrencyDaoImpl implements CurrencyDao {
    private static final CurrencyDao instance = new CurrencyDaoImpl();
    private EntityManagerFactory emf = EMFProvider.getInstance().getEmf();

    private CurrencyDaoImpl() {
    }

    public static CurrencyDao getInstance() {
        return instance;
    }

    @Override
    public List<CurrencyRate> getRates() {
        EntityManager em = emf.createEntityManager();
        List<CurrencyRate> rates = em.createQuery("FROM CurrencyRate", CurrencyRate.class).getResultList();
        em.close();
        return rates;
    }

    @Override
    public Integer getRate(Currency currency) {
        EntityManager em = emf.createEntityManager();
        Integer rate = em.createQuery("FROM CurrencyRate c WHERE c.currency = '" + currency + "'", CurrencyRate.class).getSingleResult().getRate();
        em.close();
        return rate;
    }

    @Override
    public synchronized void setRate(CurrencyRate rate) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(rate);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
