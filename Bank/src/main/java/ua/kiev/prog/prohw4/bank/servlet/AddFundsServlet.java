package ua.kiev.prog.prohw4.bank.servlet;

import ua.kiev.prog.prohw4.bank.FinanceOperations;
import ua.kiev.prog.prohw4.bank.dao.UserDao;
import ua.kiev.prog.prohw4.bank.dao.UserDaoImpl;
import ua.kiev.prog.prohw4.bank.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "AddFundsServlet", urlPatterns = "/addFunds")
public class AddFundsServlet extends HttpServlet {
    private UserDao userDao = UserDaoImpl.getInstance();
    private FinanceOperations fo = FinanceOperations.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        Integer accountId = Integer.valueOf(request.getParameter("accountId"));
        Integer amount = (int)(Float.valueOf(request.getParameter("amount")) * 100);
        fo.addFunds(accountId, amount);
        response.sendRedirect("/addFunds");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        request.setAttribute("users", userDao.gerUsers());
        if (request.getParameter("userid") != null) {
            Integer id = Integer.valueOf(request.getParameter("userid"));
            User user = userDao.getUser(id);
            request.setAttribute("accounts", user.getAccounts());
        }
        request.getRequestDispatcher("addFunds.jsp").forward(request, response);
    }
}
