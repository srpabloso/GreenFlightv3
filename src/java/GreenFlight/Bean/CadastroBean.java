
package GreenFlight.Bean;

import GreenFlight.DAO.ClienteDAO;
import GreenFlight.VO.ClienteVO;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "cadastroBean")
@ViewScoped
public class CadastroBean implements Serializable {

    private ClienteVO _Cliente;
    private String _Senha;
    private String _ConfirmacaoSenha;
    private String _DataNascimento;
    private String _Erro;
    private Boolean _Cadastrado = false;
    
    public CadastroBean() {
        _Cliente = new ClienteVO();
    }
    
    public String cadastrarCliente()
    {
        try {
            if (validarCampos())
            {
                ClienteDAO dao = new ClienteDAO();
                dao.salvar(_Cliente);
                return "/login";
            }
        } catch (Exception e) {
            
        }
        return null;
    }
    
    public Boolean validarCampos()
    {
        try {
            if (_Senha == null || !_Senha.equals(_ConfirmacaoSenha))
            {
                _Erro = "Senhas não conferem";
                return false;
            }
            else
            {
                // cript
                _Cliente.setSenha(convertStringToMd5(_Senha));
            }
            if (_Cliente == null || _Cliente.getCPF() == null || _Cliente.getCPF().trim().equals(""))
            {
                _Erro = "Todos campos são obrigatórios";
                return false;
            }
            if (_Cliente == null || _Cliente.getLogin() == null || _Cliente.getLogin().trim().equals(""))
            {
                _Erro = "Todos campos são obrigatórios";
                return false;
            }
            if (_Cliente == null || _Cliente.getNome() == null || _Cliente.getNome().trim().equals(""))
            {
                _Erro = "Todos campos são obrigatórios";
                return false;
            }
            ClienteDAO dao = new ClienteDAO();
            if (dao.loginExiste(_Cliente.getLogin()))
            {
                _Erro = "Login já existe";
                return false;
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            _Cliente.setDataNascimento(sdf.parse(_DataNascimento));
            
            return true;
        } catch (ParseException e) {
            _Erro = "Dava inválidaa";
            return false;
        }
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
    
    public ClienteVO getCliente() {
        return _Cliente;
    }

    public void setCliente(ClienteVO _Cliente) {
        this._Cliente = _Cliente;
    }

    public String getSenha() {
        return _Senha;
    }

    public void setSenha(String _Senha) {
        this._Senha = _Senha;
    }

    public String getConfirmacaoSenha() {
        return _ConfirmacaoSenha;
    }

    public void setConfirmacaoSenha(String _ConfirmacaoSenha) {
        this._ConfirmacaoSenha = _ConfirmacaoSenha;
    }

    public String getDataNascimento() {
        return _DataNascimento;
    }

    public void setDataNascimento(String _DataNascimento) {
        this._DataNascimento = _DataNascimento;
    }

    public String getErro() {
        return _Erro;
    }

    public void setErro(String _Erro) {
        this._Erro = _Erro;
    }

    public Boolean getCadastrado() {
        return _Cadastrado;
    }

    public void setCadastrado(Boolean _Cadastrado) {
        this._Cadastrado = _Cadastrado;
    }
    
    
}
