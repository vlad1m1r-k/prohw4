package ua.kiev.prog.prohw4.bank.servlet;

import ua.kiev.prog.prohw4.bank.Currency;
import ua.kiev.prog.prohw4.bank.dao.CurrencyDao;
import ua.kiev.prog.prohw4.bank.dao.CurrencyDaoImpl;
import ua.kiev.prog.prohw4.bank.entity.CurrencyRate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/rates")
public class RatesServlet extends HttpServlet {
    private CurrencyDao dao = CurrencyDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        List<CurrencyRate> rates = dao.getRates();
        for (Currency currency : Currency.values()) {
            if (currency != Currency.UAH) {
                boolean present = false;
                for (CurrencyRate rate : rates) {
                    if (rate.getCurrency() == currency) {
                        present = true;
                        break;
                    }
                }
                if (!present) {
                    rates.add(new CurrencyRate(currency, null));
                }
            }
        }
        request.setAttribute("rates", rates);
        request.getRequestDispatcher("rates.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            Currency currency = Currency.valueOf(entry.getKey());
            Integer rate = (int)(Float.valueOf(entry.getValue()[0]) * 100);
            dao.setRate(new CurrencyRate(currency, rate));
        }
        response.sendRedirect("/");
    }
}
