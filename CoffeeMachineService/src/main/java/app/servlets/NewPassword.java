package app.servlets;

import entities.DBConnect;
import entities.Logging;
import entities.MailSender;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class NewPassword extends HttpServlet {

    private String resultNewPassword = "";
    private Logging logging = new Logging();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/newpassword.jsp");
        requestDispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mail = req.getParameter("email");
        try {
            CheckUserInDB(mail);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            req.setAttribute("resultChecking",resultNewPassword);
            doGet(req, resp);
        }

    }
    public void CheckUserInDB(String mail) throws SQLException, FileNotFoundException {
        resultNewPassword = "there is no user with such mail";
        DBConnect dbConnect = new DBConnect();
        Connection connection = dbConnect.ConnectToDB();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM auth_client");
        while (resultSet.next()){
            String mailDB = resultSet.getString("email");
            if (mail == mailDB){
                MailSender mailSender = new MailSender(mail);
                String newpassword = mailSender.newpassword;
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE auth_client SET password = ? WHERE email = ?");
                preparedStatement.setString(1,newpassword);
                preparedStatement.setString(2,mailDB);
                preparedStatement.executeUpdate();
                resultNewPassword = "a new password will be sent to your mail";
                logging.DataRecording("a new password will be sent to your mail" + mail);
            }
        }
        connection.close();
    }

}
