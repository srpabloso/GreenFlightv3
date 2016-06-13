
package GreenFlight.DAO;

import GreenFlight.VO.ClienteVO;
import GreenFlight.VO.PassagemVO;
import GreenFlight.VO.VooVO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;

public class PassagemDAO extends BaseDAO {
    
    public Boolean salvar(PassagemVO passagem)
    {
        EntityTransaction transacao = getEntityManager().getTransaction();

        try {
            transacao.begin();
            getEntityManager().persist(passagem);
            transacao.commit(); 
            
            return true;
        } catch (Exception e) {
            transacao.rollback();
            return false;
        }
    }
    
    public PassagemVO buscar(long id)
    {
        try {
            PassagemVO p = getEntityManager().find(PassagemVO.class, id);
            return p;
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
    
    public List<PassagemVO> listar()
    {
        try {
            CriteriaQuery q = getEntityManager().getCriteriaBuilder().createQuery();
            q.select(q.from(PassagemVO.class));
            
            return getEntityManager().createQuery(q).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<PassagemVO> listarPorClientes(ClienteVO cliente)
    {
        try {
            CriteriaQuery q = getEntityManager().getCriteriaBuilder().createQuery();
            q.select(q.from(PassagemVO.class));
            List<PassagemVO> passagensFiltradas = new ArrayList<PassagemVO>();
            for (PassagemVO p : (List<PassagemVO>)getEntityManager().createQuery(q).getResultList())
            {
                if (p.getCliente().getCPF().equals(cliente.getCPF()))
                {
                    passagensFiltradas.add(p);
                }
            }
            return passagensFiltradas;
        } catch (Exception e) {
            return null;
        }
    }
    
}
