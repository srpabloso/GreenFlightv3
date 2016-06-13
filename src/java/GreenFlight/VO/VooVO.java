
package GreenFlight.VO;

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Voo")
public class VooVO {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "VOO_SEQUENCE")
    private long _CodigoVoo;
    
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private AeroportoVO _AeroportoOrigem;
    
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private AeroportoVO _AeroportoDestino;
    
    @Column
    private Date _DataViagem;
    
    public VooVO() {
        
    }
    
    public long getCodigoVoo() {
        return _CodigoVoo;
    }

    public void setCodigoVoo(long _CodigoVoo) {
        this._CodigoVoo = _CodigoVoo;
    }

    public Date getDataViagem() {
        return _DataViagem;
    }

    public void setDataViagem(Date _DataViagem) {
        this._DataViagem = _DataViagem;
    }

    public AeroportoVO getAeroportoOrigem() {
        return _AeroportoOrigem;
    }

    public void setAeroportoOrigem(AeroportoVO _AeroportoOrigem) {
        this._AeroportoOrigem = _AeroportoOrigem;
    }

    public AeroportoVO getAeroportoDestino() {
        return _AeroportoDestino;
    }

    public void setAeroportoDestino(AeroportoVO _AeroportoDestino) {
        this._AeroportoDestino = _AeroportoDestino;
    }
    
    
}
