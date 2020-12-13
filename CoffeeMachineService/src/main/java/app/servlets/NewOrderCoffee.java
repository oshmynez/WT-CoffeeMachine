package app.servlets;

import entities.DBConnect;
import entities.Logging;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class NewOrderCoffee extends HttpServlet {
    private String resultChecking = "no such user";
    private String loginSession;
    private Logging logging = new Logging();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/orderCoffee.jsp");
        requestDispatcher.forward(req, resp);
        HttpSession httpSession = req.getSession();
        loginSession = (String) httpSession.getAttribute("login");
        PrintWriter out = resp.getWriter();
        out.println(loginSession);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Coffee = req.getParameter("isTypeCoffee");
        String CoffeeBeans = req.getParameter("isTypeBeans");
        String Additives = req.getParameter("isAdditives");
        if (Coffee.length() == 0 || CoffeeBeans.length() == 0 || Additives.length() == 0) {
            resultChecking = "there are empty fields";
            req.setAttribute("resultChecking", resultChecking);
            doGet(req, resp);
        }
        HttpSession httpSession = req.getSession();
        loginSession = (String) httpSession.getAttribute("login");
        try {
            if (AddOrder(loginSession, Coffee, CoffeeBeans, Additives)) {
                resultChecking = "the order has been sent";
                logging.DataRecording("order successfully added: " + Coffee + "," + CoffeeBeans + "," + Additives);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            req.setAttribute("resultChecking", resultChecking);
            doGet(req, resp);
        }


    }

    public boolean AddOrder(String login, String Coffee, String CoffeeBeans, String Additives) throws SQLException {
        DBConnect dbConnect = new DBConnect();
        Connection connection = dbConnect.ConnectToDB();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO orders (Login, Coffee, CoffeeBeans, Additives,StatusOrder) VALUE (?,?,?,?,?)");
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, Coffee);
        preparedStatement.setString(3, CoffeeBeans);
        preparedStatement.setString(4, Additives);
        preparedStatement.setString(5, "Not ready");
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            connection.close();
            return true;
        }
        connection.close();
        return false;

    }
}
