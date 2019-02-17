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

@WebServlet(name = "TransferFundsServlet", urlPatterns = "/transferFunds")
public class TransferFundsServlet extends HttpServlet {
    private UserDao userDao = UserDaoImpl.getInstance();
    private FinanceOperations fo = FinanceOperations.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        Integer fromId = Integer.valueOf(request.getParameter("accountFrom"));
        Integer toId = Integer.valueOf(request.getParameter("accountTo"));
        Integer amount = (int)(Float.valueOf(request.getParameter("amount")) * 100);
        fo.transferFunds(fromId, toId, amount);
        response.sendRedirect("/transferFunds");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        request.setAttribute("users", userDao.gerUsers());
        if (request.getParameter("fromUserId") != null && !request.getParameter("fromUserId").equals("")) {
            Integer id = Integer.valueOf(request.getParameter("fromUserId"));
            User user = userDao.getUser(id);
            request.setAttribute("fromAccounts", user.getAccounts());
        }
        if (request.getParameter("toUserId") != null && !request.getParameter("toUserId").equals("")) {
            Integer id = Integer.valueOf(request.getParameter("toUserId"));
            User user = userDao.getUser(id);
            request.setAttribute("toAccounts", user.getAccounts());
        }
        request.getRequestDispatcher("transferFunds.jsp").forward(request, response);
    }
}
