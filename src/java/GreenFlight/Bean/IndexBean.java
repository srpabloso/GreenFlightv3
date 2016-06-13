
package GreenFlight.Bean;

import GreenFlight.DAO.AeroportoDAO;
import GreenFlight.DAO.ClienteDAO;
import GreenFlight.DAO.PassagemDAO;
import GreenFlight.DAO.VooDAO;
import GreenFlight.VO.AeroportoVO;
import GreenFlight.VO.ClienteVO;
import GreenFlight.VO.PassagemVO;
import GreenFlight.VO.VooVO;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.Convert;

@Named(value = "indexBean")
@ViewScoped
public class IndexBean implements Serializable {

    private String _ResultadoBanco;
    private List<String> _Estados;
    private String _DataViagem;
    private String _EstadoOrigem;
    private String _EstadoDestino;
    private String _Erro;
    
    public IndexBean() {
        _Estados = new ArrayList<String>();
        _Estados.add("SP");
        _Estados.add("RJ");
        _Estados.add("MG");
        _Estados.add("RS");
        _Estados.add("MA");
        _EstadoOrigem = _Estados.get(0);
        _EstadoDestino = _Estados.get(0);
    }
    
    public void salvarCliente(String cpf, String nome, String login, String senha, String email, String dataNascimento)
    {
        try {
            ClienteVO cliente = new ClienteVO();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d = sdf.parse(dataNascimento);
            cliente.setDataNascimento(d);
            cliente.setCPF(cpf);
            cliente.setLogin(login);
            cliente.setNome(nome);
            cliente.setSenha(senha);
            cliente.setEmail(email);

            ClienteDAO dao = new ClienteDAO();
            dao.salvar(cliente);
        } catch (ParseException e) {
            _ResultadoBanco = "Data inválida";
        }
        
    }
    
    public void buscarCliente(String cpf)
    {
        ClienteDAO dao = new ClienteDAO();
        _ResultadoBanco = dao.buscar(cpf).getNome();
    }
    
    public void excluirCliente(String cpf)
    {
        ClienteDAO dao = new ClienteDAO();
        if (dao.excluir(cpf))
            _ResultadoBanco = "Sucesso";
        else
            _ResultadoBanco = "Falha";
    }
    
    public void listarClientes()
    {
        ClienteDAO dao = new ClienteDAO();
        _ResultadoBanco = String.valueOf(dao.listar().size());
    }
    
    public void salvarAeroporto(String sigla, String nome, String estado)
    {
        AeroportoVO aeroporto = new AeroportoVO();
        aeroporto.setEstado(estado);
        aeroporto.setNome(nome);
        aeroporto.setSigla(sigla);
        AeroportoDAO dao = new AeroportoDAO();
        dao.salvar(aeroporto);
    }
    
    public void buscarAeroporto(String sigla)
    {
        AeroportoDAO dao = new AeroportoDAO();
        _ResultadoBanco = dao.buscar(sigla).getNome();
    }
    
    public void excluirAeroporto(String sigla)
    {
        AeroportoDAO dao = new AeroportoDAO();
        if (dao.excluir(sigla))
            _ResultadoBanco = "Sucesso";
        else
            _ResultadoBanco = "Falha";
    }
    
    public void listarAeroportos()
    {
        AeroportoDAO dao = new AeroportoDAO();
        _ResultadoBanco = String.valueOf(dao.listar().size());
    }
    
    public void salvarVoo(String aeroportoOrigem, String aeroportoDestino, String data)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d = sdf.parse(data);
            VooVO voo = new VooVO();
            AeroportoDAO aeroDao = new AeroportoDAO();
            voo.setAeroportoDestino(aeroDao.buscar(aeroportoDestino));
            voo.setAeroportoOrigem(aeroDao.buscar(aeroportoOrigem));
            voo.setDataViagem(d);
            VooDAO dao = new VooDAO();
            dao.salvar(voo); 
        } catch (ParseException e) {
            _ResultadoBanco = "Data Inválida";
        }
        
    }
    
    public void buscarVoo(long id)
    {
        VooDAO dao = new VooDAO();
        _ResultadoBanco = dao.buscar(id).getAeroportoDestino().getNome();
    }
    
    public void excluirVoo(long id)
    {
        VooDAO dao = new VooDAO();
        if (dao.excluir(id))
            _ResultadoBanco = "Sucesso";
        else
            _ResultadoBanco = "Falha";
    }
    
    public void listarVoos()
    {
        try {
            VooDAO dao = new VooDAO();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            validaSelecaoDeEstados();
            excluirVoo(2);
            salvarVoo("GRU", "GIG", "14/06/2016");
            List<VooVO> voos = dao.filtrar(sdf.parse(getDataViagem()), getEstadoOrigem(), getEstadoDestino());
            _Erro = "";
            //Chama a tela de listar voos
        } catch (ParseException e) {
            _Erro = "Data inválida";
        } catch (Exception e) {
            _Erro = "Estado origem e destino precisam ser diferentes";
        }
    }
    
    private void validaSelecaoDeEstados() throws Exception
    {
        if (getEstadoDestino().equals(getEstadoOrigem()))
            throw new Exception();
    }
    
    public void salvarPassagem(Integer assento, Double preco, Double desconto, Double valorTotal, long idVoo, String cpfCliente)
    {
        try {
            PassagemVO passagem = new PassagemVO();
            passagem.setAssento(assento);
            passagem.setDesconto(desconto);
            passagem.setPreco(preco);
            passagem.setValorTotal(valorTotal);
            passagem.setCliente(new ClienteDAO().buscar(cpfCliente));
            passagem.setVoo(new VooDAO().buscar(idVoo));
            PassagemDAO dao = new PassagemDAO();
            dao.salvar(passagem); 
        } catch (Exception e) {
            _ResultadoBanco = "Deu ruim";
        }
        
    }
    
    public void buscarPassagem(long id)
    {
        PassagemDAO dao = new PassagemDAO();
        _ResultadoBanco = dao.buscar(id).getCliente().getNome();
    }
    
    public String navegarParaCadastro()
    {
        return "cadastro";
    }
    
    public String navegarParaLogin()
    {
        return "login";
    }
    
    public String navegarParaHome()
    {
        return "index";
    }
    
    public void excluirPassagem(long id)
    {
        PassagemDAO dao = new PassagemDAO();
        if (dao.excluir(id))
            _ResultadoBanco = "Sucesso";
        else
            _ResultadoBanco = "Falha";
    }
    
    public void listarPassagens()
    {
        PassagemDAO dao = new PassagemDAO();
        _ResultadoBanco = String.valueOf(dao.listar().size());
    }
    
    public String getResultadoBanco() {
        return _ResultadoBanco;
    }
    
    
    public String getDataViagem() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.parse(_DataViagem);
            return _DataViagem;
        } catch (Exception e) {
            return "";
        }
    }

    public void setDataViagem(String _DataViagem) {
        this._DataViagem = _DataViagem;
    }
    public List<String> getEstados() {
        return _Estados;
    }

    public void setEstados(List<String> _Estados) {
        this._Estados = _Estados;
    }
    
    
    public String getEstadoOrigem() {
        return _EstadoOrigem;
    }

    public void setEstadoOrigem(String _EstadoOrigem) {
        this._EstadoOrigem = _EstadoOrigem;
    }

    public String getEstadoDestino() {
        return _EstadoDestino;
    }

    public void setEstadoDestino(String _EstadoDestino) {
        this._EstadoDestino = _EstadoDestino;
    }

    public String getErro() {
        return _Erro;
    }

    public void setErro(String _Erro) {
        this._Erro = _Erro;
    }
    
    
}
