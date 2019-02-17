package ua.kiev.prog.prohw4.menu.servlet;

import ua.kiev.prog.prohw4.menu.Criteria;
import ua.kiev.prog.prohw4.menu.dao.MenuDao;
import ua.kiev.prog.prohw4.menu.dao.MenuDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/")
public class MenuServlet extends HttpServlet {
    private MenuDao dao = MenuDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        request.setAttribute("dishes", dao.getDishes());
        request.getRequestDispatcher("menu.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        Criteria criteria = new Criteria();
        criteria.setPriceFrom(request.getParameter("priceFrom").equals("") ? null : Integer.valueOf(request.getParameter("priceFrom")) * 100);
        criteria.setPriceTo(request.getParameter("priceTo").equals("") ? null : Integer.valueOf(request.getParameter("priceTo")) * 100);
        criteria.setDiscount(request.getParameter("discount") != null);
        criteria.setWeightTo(request.getParameter("weightTo").equals("") ? null : (int) (Float.valueOf(request.getParameter("weightTo")) * 1000));
        request.setAttribute("dishes", dao.getDishes(criteria));
        request.getRequestDispatcher("menu.jsp").forward(request, response);
    }
}
