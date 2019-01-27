package database;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static Session session;

    private static void createInstance() {
        if (sessionFactory != null) {
            return;
        }
        Configuration conf = new Configuration();
        conf.addAnnotatedClass(UserDetails.class);
        conf.addAnnotatedClass(BookDetails.class);
        conf.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        try {
            sessionFactory = conf.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }


    public static Session getDatabaseSession() {
        createInstance();
        if (session != null && session.isOpen()) {
            return session;
        }
        session = sessionFactory.openSession();
        return session;
    }
}