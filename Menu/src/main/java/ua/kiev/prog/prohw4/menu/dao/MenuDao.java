package ua.kiev.prog.prohw4.menu.dao;

import ua.kiev.prog.prohw4.menu.Criteria;
import ua.kiev.prog.prohw4.menu.entity.Dish;

import java.util.List;

public interface MenuDao {
    List<Dish> getDishes();
    List<Dish> getDishes(Criteria criteria);

    void addDish(Dish dish);
}
