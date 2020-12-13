package app.servlets;
import entities.DBConnect;
import model.Order;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Queue;
import java.util.*;

public class Orders extends HttpServlet {
    private String resultChecking = "";
    private String login;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/orders.jsp");
        requestDispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Queue<Order> orderList = new Queue<Order>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Order> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Order order) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Order> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean offer(Order order) {
                return false;
            }

            @Override
            public Order remove() {
                return null;
            }

            @Override
            public Order poll() {
                return null;
            }

            @Override
            public Order element() {
                return null;
            }

            @Override
            public Order peek() {
                return null;
            }
        };
        login = "";
        try {
            if (!GetOrders(orderList)){
                resultChecking = "no current orders";
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }finally {
            req.setAttribute("resultChecking",resultChecking);
            req.setAttribute("ListOrders",orderList);
            doGet(req, resp);
        }
    }

    public boolean GetOrders(Queue<Order> arrayListOrder) throws SQLException {
        DBConnect dbConnect = new DBConnect();
        Connection connection = dbConnect.ConnectToDB();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");
        int rowsAdd = 0;
        while (resultSet.next()){
            String loginDB = resultSet.getString("Login");

            if (login == loginDB){
                Order order = new Order();
                order.NameCoffee = resultSet.getString("Coffee");
                order.NameBeans = resultSet.getString("CoffeeBeans");
                order.NameAdditives = resultSet.getString("Additives");
                order.StatusOrder = resultSet.getString("StatusOrder");
                arrayListOrder.add(order);
                rowsAdd++;
            }
        }
        if (rowsAdd > 0) {
            connection.close();
            return true;
        }
        connection.close();
        return false;

    }
}
