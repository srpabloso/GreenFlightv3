
package GreenFlight.VO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Aeroporto")
public class AeroportoVO {

    @Id
    private String _Sigla;
    
    @Column
    private String _Nome;
    
    @Column
    private String _Estado;

    public AeroportoVO()
    { }
   
    public String getNome() {
        return _Nome;
    }

    public void setNome(String _Nome) {
        this._Nome = _Nome;
    }
    
    public void setSigla(String _Sigla) {
        this._Sigla = _Sigla;
    }

    public String getSigla() {
        return _Sigla;
    }

    public String getEstado() {
        return _Estado;
    }

    public void setEstado(String _Estado) {
        this._Estado = _Estado;
    }
    
    
}
