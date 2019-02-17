package ua.kiev.prog.prohw4.bank;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFProvider {
    private static final EMFProvider instance = new EMFProvider();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bank");

    private EMFProvider() {
    }

    public static EMFProvider getInstance() {
        return instance;
    }

    public EntityManagerFactory getEmf(){
        return emf;
    }

    public void close() {
        emf.close();
    }
}
