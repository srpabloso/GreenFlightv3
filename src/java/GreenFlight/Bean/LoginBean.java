/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GreenFlight.Bean;

import javax.inject.Named;
import GreenFlight.DAO.ClienteDAO;
import GreenFlight.VO.ClienteVO;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrador
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
private ClienteDAO usuarioDAO = new ClienteDAO();
        private ClienteVO usuario = new ClienteVO();
        
        public String envia() {
              
            try{
              usuario = usuarioDAO.validar(usuario.getLogin(), usuario.getSenha());
              //usuario = usuarioDAO.buscar(usuario.getLogin());
              if (usuario == null) {
                    usuario = new ClienteVO();
                    FacesContext.getCurrentInstance().addMessage(
                               null,
                               new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                                           "Erro no Login!"));
                    return null;
              } else {
                    return "/index";
              }
            }
            catch(Exception ex)
            {
               
            }
              return null;
        }
        
        public ClienteVO getUsuario()
        {
            return usuario;
        }
        public void setUsuario(ClienteVO client)
        {
            usuario = client;
        }
        
        public Boolean autenticado()
        {
            return usuario.getCPF() != null;
        }
}
