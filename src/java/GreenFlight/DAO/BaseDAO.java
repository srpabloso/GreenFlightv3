
package GreenFlight.DAO;

import GreenFlight.BO.HibernateUtil;
import javax.persistence.EntityManager;

public class BaseDAO {
    
    protected EntityManager hinernateManager;
    
    protected EntityManager getEntityManager() 
    {
        if (hinernateManager == null) {
            hinernateManager = HibernateUtil.getInstance().createEntityManager();
        }
        return hinernateManager;
    }
}
