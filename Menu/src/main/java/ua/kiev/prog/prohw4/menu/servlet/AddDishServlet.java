package ua.kiev.prog.prohw4.menu.servlet;

import ua.kiev.prog.prohw4.menu.dao.MenuDao;
import ua.kiev.prog.prohw4.menu.dao.MenuDaoImpl;
import ua.kiev.prog.prohw4.menu.entity.Dish;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "AddDishServlet", urlPatterns = "/add")
public class AddDishServlet extends HttpServlet {
    private MenuDao dao = MenuDaoImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        String name = request.getParameter("name");
        Integer price = (int) (Float.valueOf(request.getParameter("price")) * 100);
        Integer weight = (int) (Float.valueOf(request.getParameter("weight")) * 1000);
        Boolean discount = request.getParameter("discount") != null;
        Dish dish = new Dish(name, price, weight, discount);
        dao.addDish(dish);
        response.sendRedirect("/");
    }
}
