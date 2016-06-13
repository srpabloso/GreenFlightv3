
package GreenFlight.VO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class ClienteVO {
    
    @Id
    private String _CPF;
    @Column
    private String _Nome;
    @Column
    private String _Login;
    @Column
    private String _Senha;
    @Column
    private Date _DataNascimento;
    @Column
    private String _Email;

    public ClienteVO()
    {}
    
    public String getEmail() {
        return _Email;
    }

    public void setEmail(String _Email) {
        this._Email = _Email;
    }
    
    public String getCPF() {
        return _CPF;
    }

    public void setCPF(String _CPF) {
        this._CPF = _CPF;
    }

    public String getNome() {
        return _Nome;
    }

    public void setNome(String _Nome) {
        this._Nome = _Nome;
    }

    public String getLogin() {
        return _Login;
    }

    public void setLogin(String _Login) {
        this._Login = _Login;
    }

    public String getSenha() {
        return _Senha;
    }

    public void setSenha(String _Senha) {
        this._Senha = _Senha;
    }

    public Date getDataNascimento() {
        return _DataNascimento;
    }

    public void setDataNascimento(Date _DataNascimento) {
        this._DataNascimento = _DataNascimento;
    }
}
