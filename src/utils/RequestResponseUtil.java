package utils;

import database.BookDetails;
import database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RequestResponseUtil {


    public static String getUsername(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for(int i=0;i<cookies.length;i++) {
            Cookie cookie = cookies[i];
            System.out.println(cookie.getName() + " : " +cookie.getValue());
            if(cookie.getName().equals("username")) {
                return cookie.getValue();
            }
        }
        return null;
    }
    public static void setAttributes(HttpServletRequest request) {


        String username = getUsername(request);

        setAttributes(request,username);
    }

    public static void setAttributes(HttpServletRequest request,String username) {
        Session session = HibernateUtil.getDatabaseSession();
        Transaction transaction = session.beginTransaction();


        Query query = session.createQuery("FROM BookDetails where userDetails.username = :username");
        query.setParameter("username", username);

        List<BookDetails> bookDetails = (List<BookDetails>)query.list();

        request.setAttribute("mybooks", bookDetails);

        query = session.createQuery("FROM BookDetails where userDetails.username != :username");
        query.setParameter("username", username);

        bookDetails = (List<BookDetails>)query.list();


        request.setAttribute("othersbooks", bookDetails);

        transaction.commit();
        session.close();
    }
}
