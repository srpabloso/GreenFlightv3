
package GreenFlight.VO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Passagem")
public class PassagemVO {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "PASSAGEM_SEQUENCE")
    private long _CodigoPassagem;
    
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private VooVO _Voo;
    
    @Column
    private Integer _Assento;
    
    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private ClienteVO _Cliente;
    
    @Column
    private Double _Preco;
    
    @Column
    private Double _Desconto;
    
    @Column
    private Double _ValorTotal;
    
    public PassagemVO() {
    }
    
    public PassagemVO(Integer codigo, VooVO voo, ClienteVO cliente, Double preco, Double desconto, Double valorTotal) {
        setCodigoPassagem(codigo);
        setVoo(voo);
        setCliente(cliente);
        setPreco(preco);
        setDesconto(desconto);
        setValorTotal(valorTotal);
    }

    public long getCodigoPassagem() {
        return _CodigoPassagem;
    }

    public void setCodigoPassagem(long _CodigoPassagem) {
        this._CodigoPassagem = _CodigoPassagem;
    }

    public VooVO getVoo() {
        return _Voo;
    }

    public void setVoo(VooVO _Voo) {
        this._Voo = _Voo;
    }

    public Integer getAssento() {
        return _Assento;
    }

    public void setAssento(Integer _Assento) {
        this._Assento = _Assento;
    }

    public ClienteVO getCliente() {
        return _Cliente;
    }

    public void setCliente(ClienteVO _Cliente) {
        this._Cliente = _Cliente;
    }

    public Double getPreco() {
        return _Preco;
    }

    public void setPreco(Double _Preco) {
        this._Preco = _Preco;
    }

    public Double getDesconto() {
        return _Desconto;
    }

    public void setDesconto(Double _Desconto) {
        this._Desconto = _Desconto;
    }

    public Double getValorTotal() {
        return _ValorTotal;
    }

    public void setValorTotal(Double _ValorTotal) {
        this._ValorTotal = _ValorTotal;
    }
    
}
