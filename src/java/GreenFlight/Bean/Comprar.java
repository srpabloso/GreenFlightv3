/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GreenFlight.Bean;

import javax.ejb.Stateful;
import GreenFlight.VO.PassagemVO;

/**
 *
 * @author 37761832846
 */
@Stateful
public class Comprar {
    private PassagemVO _Passagem;
    
    public void setPassagem(PassagemVO pass)
    {
        _Passagem = pass;
    }
    
    public PassagemVO getPassagem()
    {
        return _Passagem;
    }
    
    
    
}
