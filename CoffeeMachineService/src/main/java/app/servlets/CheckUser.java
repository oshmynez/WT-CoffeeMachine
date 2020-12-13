package app.servlets;
import entities.DBConnect;
import entities.Logging;
import entities.User;
import model.Model;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckUser extends HttpServlet {
    private String resultChecking = "no such user";
    private Integer resultDispatcher = 0;
    private String login;
    private Logging logging = new Logging();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        switch (resultDispatcher){
            case 0:{
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/login.jsp");
                requestDispatcher.forward(req, resp);
            break;
            }
            case 1:{
                httpSession.setAttribute("login",login);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/orderCoffee.jsp");
                requestDispatcher.forward(req, resp);
            break;
            }

            default:
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login = req.getParameter("login");

        String password = req.getParameter("pass");
        User user = new User(login, password);
        Model model = Model.getInstance();
        //model.add(user);
        try {
             if (CheckUserInDB(login,password)){
                 resultDispatcher = 1;
                 resultChecking = "Welcome, "+ login;
                 logging.DataRecording(login+ ", successfully LogIn");
             } else{
                 resultDispatcher = 0;
                 resultChecking = "no such user";
                 logging.DataRecording(login+ ", not successfully LogIn");
             }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            req.setAttribute("resultChecking",resultChecking);
            doGet(req, resp);
        }

    }
    public boolean CheckUserInDB(String login,String password) throws SQLException {
        DBConnect dbConnect = new DBConnect();
        Connection connection = dbConnect.ConnectToDB();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT login, password FROM auth_client");

        while (resultSet.next()){
            String passwordDB = resultSet.getString("password");
            String loginDB = resultSet.getString("login");

            if (login.equals(loginDB) && password.equals(passwordDB)){
                connection.close();
                return true;
            }
        }
        connection.close();
        return false;
    }
}
