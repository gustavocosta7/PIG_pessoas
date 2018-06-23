/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.CidadeDAO;
import exception.DAOException;
import java.util.ArrayList;
import java.util.List;

import modelo.Cidade;

/**
 *
 * @author Aluno
 */
public class CidadeControle {
    private Cidade cidade = new Cidade();
    private List<Cidade> cidades = new ArrayList<>();
  
    public void setCidade(Cidade c) {
       this.cidade = c;
    }

    public Cidade getCidade() {
        return this.cidade;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void listar() throws DAOException {
        CidadeDAO dao = new CidadeDAO();
        this.cidades = dao.listarCidade();
    }
    
}
