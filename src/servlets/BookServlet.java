package servlets;

import database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.RequestResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookServlet",urlPatterns = {"/book"})
public class BookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username= RequestResponseUtil.getUsername(request);
        String[] options=request.getParameterValues("bookids");
        List<Integer> bookids =new ArrayList<>();
        for(String option:options){
            bookids.add(Integer.parseInt(option));
        }
        Session session= HibernateUtil.getDatabaseSession();
        Transaction transaction= session.beginTransaction();

        Query query = session.createQuery("UPDATE BookDetails set userDetails.username=:username where bookid in :books");
        query.setParameter("username",username);
        query.setParameter("books",bookids);
        query.executeUpdate();

        transaction.commit();
        session.close();

        RequestResponseUtil.setAttributes(request);

        request.getRequestDispatcher("/homepage.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
