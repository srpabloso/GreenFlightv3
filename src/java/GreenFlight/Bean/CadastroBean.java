
package GreenFlight.Bean;

import GreenFlight.DAO.ClienteDAO;
import GreenFlight.VO.ClienteVO;
import java.io.Serializable;
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
    
    public CadastroBean() {
        _Cliente = new ClienteVO();
    }
    
    public void cadastrarCliente()
    {
        try {
            if (validarCampos())
            {
                ClienteDAO dao = new ClienteDAO();
                dao.salvar(_Cliente);
                LoginBean b = new LoginBean();
                b.setUsuario(_Cliente);
                b.envia();
            }
        } catch (Exception e) {
            
        }
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
                _Cliente.setSenha(_Senha);
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
}
