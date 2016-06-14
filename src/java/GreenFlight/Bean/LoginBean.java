/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GreenFlight.Bean;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import GreenFlight.DAO.ClienteDAO;
import GreenFlight.VO.ClienteVO;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Administrador
 */
@Named(value = "loginBean")
@ViewScoped
public class LoginBean {
private ClienteDAO usuarioDAO = new ClienteDAO();
        private ClienteVO usuario = new ClienteVO();
        
        public String envia() {
              
              //usuario = usuarioDAO.buscar(usuario.getLogin(), usuario.getSenha());
              usuario = usuarioDAO.buscar(usuario.getLogin());
              if (usuario == null) {
                    usuario = new ClienteVO();
                    FacesContext.getCurrentInstance().addMessage(
                               null,
                               new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                                           "Erro no Login!"));
                    return null;
              } else {
                    return "/main";
              }
              
              
        }
}
