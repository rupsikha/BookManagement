package servlets;

import database.BookDetails;
import database.HibernateUtil;
import database.UserDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.RequestResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet",urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username + " : " +password);
        Session session = HibernateUtil.getDatabaseSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM UserDetails where username=:username and password=:password");
        query.setParameter("username",username);
        query.setParameter("password",password);
        List<UserDetails> userDetailsList = (List<UserDetails>)query.list();
        for(UserDetails userDetails : userDetailsList) {
            System.out.println(userDetails);
        }

        transaction.commit();
        session.close();

        if(userDetailsList.size() == 1) {

            Cookie cookie = new Cookie("username",username);
            response.addCookie(cookie);


            RequestResponseUtil.setAttributes(request,username);

            request.setAttribute("username",username);
            request.getRequestDispatcher("/homepage.jsp").forward(request,response);
        }
        else {
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookies[] = request.getCookies();
        String username = RequestResponseUtil.getUsername(request);

        if(username == null) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        else {
            request.setAttribute("username",username);
            RequestResponseUtil.setAttributes(request);
            request.getRequestDispatcher("/homepage.jsp").forward(request,response);
        }
    }




}
