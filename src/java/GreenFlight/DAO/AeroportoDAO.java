
package GreenFlight.DAO;

import GreenFlight.VO.AeroportoVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class AeroportoDAO extends BaseDAO {
    
    public void salvar (AeroportoVO aeroporto)
    {
        EntityTransaction transacao = getEntityManager().getTransaction();

        try {
            transacao.begin();
            getEntityManager().persist(aeroporto);
            transacao.commit(); 
        } catch (Exception e) {
            transacao.rollback();
        }
    }
    
    public AeroportoVO buscar(String sigla)
    {
        try {
            return getEntityManager().find(AeroportoVO.class, sigla);
        } catch (Exception e) {
            return null;
        }
    }
    
    public Boolean excluir(String sigla)
    {
        EntityTransaction transacao = getEntityManager().getTransaction();

        try {
            transacao.begin();
            getEntityManager().remove(buscar(sigla));
            transacao.commit();
            return true;
        } catch (Exception e) {
            transacao.rollback();
            return false;
        }
    }
    
    public List<AeroportoVO> listar()
    {
        try {
            
            CriteriaQuery q = getEntityManager().getCriteriaBuilder().createQuery();
            q.select(q.from(AeroportoVO.class));
            
            
            return getEntityManager().createQuery(q).getResultList();
        } catch (Exception e) {
            return null;
        }
    } 
}
