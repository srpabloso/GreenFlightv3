
package GreenFlight.DAO;

import GreenFlight.VO.ClienteVO;
import GreenFlight.VO.VooVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;

public class VooDAO extends BaseDAO {
    
    public Boolean salvar(VooVO voo)
    {
        EntityTransaction transacao = getEntityManager().getTransaction();

        try {
            transacao.begin();
            getEntityManager().persist(voo);
            transacao.commit(); 
            
            return true;
        } catch (Exception e) {
            transacao.rollback();
            return false;
        }
    }
    
    public VooVO buscar(long id)
    {
        try {
            VooVO v = getEntityManager().find(VooVO.class, id);
            return v;
        } catch (Exception e) {
            return null;
        }
    }
    
    public Boolean excluir(long id)
    {
        EntityTransaction transacao = getEntityManager().getTransaction();

        try {
            transacao.begin();
            getEntityManager().remove(buscar(id));
            transacao.commit();
            return true;
        } catch (Exception e) {
            transacao.rollback();
            return false;
        }
    }
    
    public List<VooVO> listar()
    {
        try {
            
            CriteriaQuery q = getEntityManager().getCriteriaBuilder().createQuery();
            q.select(q.from(VooVO.class));
            
            return getEntityManager().createQuery(q).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<VooVO> filtrar(Date dataViagem, String estadoOrigem, String estadoDestino)
    {
        try {
            List<VooVO> voosFiltrados = new ArrayList<VooVO>();
            for (VooVO v : listar())
            {
                if (v.getDataViagem().compareTo(dataViagem) == 0 && v.getAeroportoOrigem().getEstado().equals(estadoOrigem) && v.getAeroportoDestino().getEstado().equals(estadoDestino))
                {
                    voosFiltrados.add(v);   
                }
            }
            return voosFiltrados;
        } catch (Exception e) {
            return null;
        }
    }
}
