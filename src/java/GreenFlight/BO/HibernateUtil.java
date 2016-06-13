
package GreenFlight.BO;

import com.fasterxml.classmate.AnnotationConfiguration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.*;  
import org.hibernate.cfg.*;  

public class HibernateUtil {
    
    private static final HibernateUtil instance = new HibernateUtil();
 
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("GreenFlightv2PU");

    private HibernateUtil() {}

    public static HibernateUtil getInstance() {
        try {
            return instance;
        } catch (Exception e) {
            return null;
        }
    }
 
    public EntityManagerFactory getEntityManagerFactory() {
        try {
            return entityManagerFactory;
        } catch (Exception e) {
            return null;
        }
    }

    public EntityManager createEntityManager() {
        try {
            return entityManagerFactory.createEntityManager();
        } catch (Exception e) {
            return null;
        }
    }
}
