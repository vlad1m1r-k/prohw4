package ua.kiev.prog.prohw4.bank.servlet;

import ua.kiev.prog.prohw4.bank.Currency;
import ua.kiev.prog.prohw4.bank.dao.UserDao;
import ua.kiev.prog.prohw4.bank.dao.UserDaoImpl;
import ua.kiev.prog.prohw4.bank.entity.Account;
import ua.kiev.prog.prohw4.bank.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "AccountsServlet", urlPatterns = "/accounts")
public class AccountsServlet extends HttpServlet {
    private UserDao userDao = UserDaoImpl.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        User user = userDao.getUser(Integer.valueOf(request.getParameter("userId")));
        Currency currency = Currency.valueOf(request.getParameter("currency"));
        Account account = new Account(currency, user, 0);
        userDao.updateUser(user, account);
        response.sendRedirect("/accounts");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        request.setAttribute("users", userDao.gerUsers());
        request.setAttribute("currencies", Currency.values());
        if (request.getParameter("userid") != null) {
            Integer id = Integer.valueOf(request.getParameter("userid"));
            User user = userDao.getUser(id);
            request.setAttribute("accounts", user.getAccounts());
        }
        request.getRequestDispatcher("accounts.jsp").forward(request, response);
    }
}
