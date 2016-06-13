
package GreenFlight.VO;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Estado")
public class EstadoVO {
    
    @Id
    @Column(name = "sigla")
    private String _Sigla;
    @Column (unique = true)
    private String _Nome;
    
    public EstadoVO(String nome, String sigla) {
        setNome(nome);
        setSigla(sigla);
    }

    public String getNome() {
        return _Nome;
    }

    public void setNome(String _Nome) {
        this._Nome = _Nome;
    }

    public String getSigla() {
        return _Sigla;
    }

    public void setSigla(String _Sigla) {
        this._Sigla = _Sigla;
    }
    
}
