package ua.kiev.prog.prohw4.menu.dao;

import ua.kiev.prog.prohw4.menu.Criteria;
import ua.kiev.prog.prohw4.menu.entity.Dish;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDao {
    private static final MenuDaoImpl instance = new MenuDaoImpl();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Menu");

    private MenuDaoImpl() {
    }

    public static MenuDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Dish> getDishes() {
        EntityManager em = emf.createEntityManager();
        List<Dish> dishes = em.createQuery("FROM Dish", Dish.class).getResultList();
        em.close();
        return dishes;
    }

    @Override
    public List<Dish> getDishes(Criteria criteria) {
        String query = parseCriteria(criteria);
        EntityManager em = emf.createEntityManager();
        List<Dish> dishes = em.createQuery("FROM Dish c " + query, Dish.class).getResultList();
        em.close();
        if (criteria.getWeightTo() != null) {
            dishes = weightFilter(dishes, criteria.getWeightTo());
        }
        return dishes;
    }

    @Override
    public synchronized void addDish(Dish dish) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(dish);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    private String parseCriteria(Criteria criteria) {
        StringBuilder result = new StringBuilder();
        if (criteria.isDataPresent()) {
            result.append("WHERE ");
            if (criteria.getPriceFrom() != null) {
                result.append("c.price >= " + criteria.getPriceFrom() + " AND ");
            }
            if (criteria.getPriceTo() != null) {
                result.append("c.price <= " + criteria.getPriceTo() + " AND ");
            }
            if (criteria.getDiscount()) {
                result.append("c.discount = true AND ");
            }
            if (criteria.getWeightTo() != null) {
                result.append("c.weight <= " + criteria.getWeightTo() + " AND ");
            }
            result = new StringBuilder(result.substring(0, result.length() - 5));
        }
        return result.toString();
    }

    private List<Dish> weightFilter(List<Dish> dishes, int weight) {
        dishes.sort((o1, o2) -> o2.getWeight() - o1.getWeight());
        List<Dish> newDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (getWeight(newDishes)  + dish.getWeight() <= weight) {
                newDishes.add(dish);
            }
        }
        return newDishes;
    }

    private int getWeight(List<Dish> dishes) {
        int weight = 0;
        for (Dish dish : dishes) {
            weight += dish.getWeight();
        }
        return weight;
    }
}
