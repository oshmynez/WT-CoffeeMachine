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
import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

public class AddUser extends HttpServlet {

    private String email;
    private String password;
    private String login;
    public boolean signupStat;
    private String resultChecking = "text";
    private Integer resultDispatcher = 0;
    private Logging logging = new Logging();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        switch (resultDispatcher){
            case 0:{
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/signup.jsp");
                requestDispatcher.forward(req, resp);
                break;
            }
            case 1:{
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/orderCoffee.jsp");
                requestDispatcher.forward(req, resp);
                break;
            }
            default:
        }

    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        signupStat = true;
        email = req.getParameter("email");
        password = req.getParameter("pass");
        login = req.getParameter("name");
        User user = new User(login, password);
        Model model = Model.getInstance();
        //model.add(user);
        try {
            if (CheckUserInDB()){
                if (AddUserInDB()){
                    resultDispatcher = 1;
                    resultChecking = "you are registered successfully";
                    logging.DataRecording(login+ ", registered successfully");
                }else {
                    resultChecking = "you are registered not successfully";
                    resultDispatcher = 0;
                    logging.DataRecording(login+ ", registered not successfully");
                }
            }else{
                resultChecking = "user with this login exists";
                resultDispatcher = 0;
                logging.DataRecording(login+ ", user with this login exists");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        req.setAttribute("resultRegistration", resultChecking);
        doGet(req, resp);
    }

    public boolean CheckUserInDB() throws SQLException {
        DBConnect dbConnect = new DBConnect();
        Connection connection = dbConnect.ConnectToDB();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM auth_client");

        while (resultSet.next()){
            String emailDB = resultSet.getString("email");
            String loginDB = resultSet.getString("login");
            if ((login == loginDB) || (email == emailDB)){
                signupStat = false;
            }
        }

        connection.close();
        return signupStat;
    }
    public boolean AddUserInDB() {
        try {
            DBConnect dbConnect = new DBConnect();
            Connection connection = dbConnect.ConnectToDB();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO auth_client (login, password, email)  VALUE (?,?,?)");
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,email);
            int i = preparedStatement.executeUpdate();
            connection.close();
            System.out.println("register yes");
            if (i > 0) {
                return true;
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return false;
    }
}
