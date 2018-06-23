/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cidade;
import util.ControlaConexao;

/**
 *
 * @author Aluno
 */
public class CidadeDAO {

    Cidade cidade;

    public List<Cidade> listarCidade() throws DAOException {
         List<Cidade> cidades = null;
        try {
          cidades = new ArrayList<>();
            Connection concexao = ControlaConexao.getConection();
            String comando = "select cidid, cidnome, ciduf from cidade";
            PreparedStatement instrucao = concexao.prepareStatement(comando);
            ResultSet resultado = instrucao.executeQuery();
   
            while (resultado.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(resultado.getInt("cidid"));
                cidade.setNome(resultado.getString("cidnome"));
                cidade.setUf(resultado.getString("ciduf"));
                cidades.add(cidade);
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }

        return cidades;

    }
    
     public Cidade consultarPorId(Cidade c) throws DAOException {
         
         Cidade cidade = null;
        
        try {
        
            Connection concexao = ControlaConexao.getConection();
            String comando = "select cidid, cidnome, ciduf from cidade where cidid = ?";
            PreparedStatement instrucao = concexao.prepareStatement(comando);
            instrucao.setInt(1, c.getId());
            ResultSet resultado = instrucao.executeQuery();
   
            while (resultado.next()) {
                cidade = new Cidade();
                cidade.setId(resultado.getInt("cidid"));
                cidade.setNome(resultado.getString("cidnome"));
                cidade.setUf(resultado.getString("ciduf"));
             }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }

        return cidade;

    }
}
