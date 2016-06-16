
package GreenFlight.DAO;

import GreenFlight.VO.ClienteVO;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    
    public ClienteVO validar(String login, String senha)
    {
        for (ClienteVO c : listar())
        {
            if (c.getLogin().equals(login) && c.getSenha().equals(convertStringToMd5(senha)))
                return c;
        }
        return null;
    }
    
     private String convertStringToMd5(String valor) {
         MessageDigest mDigest;
         try { 
                //Instanciamos o nosso HASH MD5, poderíamos usar outro como
                //SHA, por exemplo, mas optamos por MD5.
               mDigest = MessageDigest.getInstance("MD5");
                      
               //Convert a String valor para um array de bytes em MD5
               byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));
               
               //Convertemos os bytes para hexadecimal, assim podemos salvar
               //no banco para posterior comparação se senhas
               StringBuffer sb = new StringBuffer();
               for (byte b : valorMD5){
                      sb.append(Integer.toHexString((b & 0xFF) |
                      0x100).substring(1,3));
               }
   
               return sb.toString();
                      
         } catch (NoSuchAlgorithmException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
               return null;
         } catch (UnsupportedEncodingException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
               return null;
         }
     }
    
    
    
    private Boolean clienteExiste(String cpf)
    {
        return buscar(cpf) != null;
    }
    
    public Boolean loginExiste(String login)
    {
        for (ClienteVO c : listar())
        {
            if (c.getLogin().equals(login))
            {
                return true;
            }
            
        }
        return false;
    }
}
