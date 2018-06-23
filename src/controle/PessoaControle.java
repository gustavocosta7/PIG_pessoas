/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.PessoaDAO;
import exception.DAOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Pessoa;

/**
 *
 * @author Aluno
 */
public class PessoaControle {

    private Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas = new ArrayList<>();
  
    public void setPessoa(Pessoa p) {
       this.pessoa = p;
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    
    public void inserir() throws SQLException, DAOException {
        PessoaDAO dao = new PessoaDAO();
        dao.inserir(this.pessoa);
    }
    
    public void listar() throws SQLException, DAOException{
        PessoaDAO dao = new PessoaDAO();
        this.pessoas = dao.listarRegistros();
    }
    
    public void alterar() throws DAOException, SQLException{
        PessoaDAO dao = new PessoaDAO();
        dao.alterar(this.pessoa);
    }
    
    public void excluir() throws SQLException, DAOException{
        PessoaDAO dao = new PessoaDAO();
        dao.delete(this.pessoa);
    }
    
    public void selecionar() throws SQLException, DAOException{
        PessoaDAO dao = new PessoaDAO();
        this.pessoas = dao.selecionarRegistro(this.pessoa);
    }
}
