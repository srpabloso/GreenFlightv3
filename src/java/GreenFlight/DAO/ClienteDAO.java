
package GreenFlight.DAO;

import GreenFlight.VO.ClienteVO;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;

public class ClienteDAO extends BaseDAO{
        
    public Boolean salvar(ClienteVO cliente)
    {
        EntityTransaction transacao = getEntityManager().getTransaction();

        try {
            if (!clienteExiste(cliente.getCPF()))
            {
                transacao.begin();
                getEntityManager().persist(cliente);
                transacao.commit(); 
            }
            else
            {
                transacao.begin();
                getEntityManager().merge(cliente);
                transacao.commit();
            }
            
            return true;
        } catch (Exception e) {
            transacao.rollback();
            return false;
        }
    }
    
    public ClienteVO buscar(String cpf)
    {
        try {
            return getEntityManager().find(ClienteVO.class, cpf);
        } catch (Exception e) {
            return null;
        }
    }
    
    public Boolean excluir(String cpf)
    {
        EntityTransaction transacao = getEntityManager().getTransaction();

        try {
            transacao.begin();
            getEntityManager().remove(buscar(cpf));
            transacao.commit();
            return true;
        } catch (Exception e) {
            transacao.rollback();
            return false;
        }
    }
    
    public List<ClienteVO> listar()
    {
        try {
            
            CriteriaQuery q = getEntityManager().getCriteriaBuilder().createQuery();
            q.select(q.from(ClienteVO.class));
            
            return getEntityManager().createQuery(q).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    private Boolean clienteExiste(String cpf)
    {
        return buscar(cpf) != null;
    }
}
