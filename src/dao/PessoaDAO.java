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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.Cidade;
import modelo.Pessoa;
import util.ControlaConexao;

/**
 *
 * @author Aluno
 */
public class PessoaDAO {

    public void inserir(Pessoa p) throws SQLException, DAOException {
        Connection conexao = ControlaConexao.getConection();
        try {
            String comando = "insert into pessoa(pesnome, pescpf, pesnascimento, pessexo, pescidid) values(?, ?, ?, ?, ?)";
            PreparedStatement st = conexao.prepareStatement(comando);
            st.setString(1, p.getNome());
            st.setString(2, p.getCpf());
            st.setDate(3, new java.sql.Date(p.getNascimento().getTimeInMillis()));
            st.setString(4, String.valueOf(p.getSexo()));
            st.setInt(5, p.getCidade().getId());
            st.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ControlaConexao.closeConexao(conexao);
        }
    }

    public void alterar(Pessoa p) throws DAOException, SQLException {
        Connection conexao = ControlaConexao.getConection();
        try {

            String sql = "UPDATE pessoa SET pesnome=?, pescpf=?, pesnascimento=?, pessexo=? WHERE ID=?";
            PreparedStatement instrucao = conexao.prepareStatement(sql);

            instrucao.setString(1, p.getNome());
            instrucao.setString(2, p.getCpf());
            instrucao.setDate(3, new java.sql.Date(p.getNascimento().getTimeInMillis()));
            instrucao.setString(4, String.valueOf(p.getSexo()));
            instrucao.setInt(5, p.getId());

            instrucao.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            ControlaConexao.closeConexao(conexao);
        }
    }

    public void delete(Pessoa p) throws SQLException, DAOException {
        Connection conexao = ControlaConexao.getConection();
        try {
            String comando = "delete from pessoa where id=?";
            PreparedStatement st = conexao.prepareStatement(comando);
            st.setInt(1, p.getId());
            st.execute();
        } catch (SQLException ex) {
            System.out.println("não conectado");
        } finally {
            ControlaConexao.closeConexao(conexao);
        }
    }

    public List<Pessoa> selecionarRegistro(Pessoa p) throws SQLException, DAOException {
        Connection conexao = ControlaConexao.getConection();
        List<Pessoa> pessoas = new ArrayList<>();
        try {

            String sql = "SELECT pesid, pesnome, pescpf, pesnascimento, pessexo, pescidid FROM pessoa WHERE pesnome LIKE ? "; // and pescpf like ?
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, "%" + p.getNome() + "%");
            //st.setString(2, "%" + p.getCpf() + "%");
            ResultSet resultados = st.executeQuery();
            while (resultados.next()) {
                Pessoa pes = new Pessoa();
                pes.setId(resultados.getInt("pesid"));
                pes.setNome(resultados.getString("pesnome"));
                pes.setCpf(resultados.getString("pescpf"));

                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                format.format(resultados.getDate("pesnascimento"));
                pes.setNascimento(format.getCalendar());
                Cidade cidade = new Cidade();
                cidade.setId(resultados.getInt("pescidid"));
                CidadeDAO dao = new CidadeDAO();
                pes.setCidade(dao.consultarPorId(cidade));
                pes.setSexo(resultados.getString("pessexo").charAt(0));
                pessoas.add(pes);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ControlaConexao.closeConexao(conexao);
        }
        return pessoas;
    }

    public List<Pessoa> listarRegistros() throws SQLException, DAOException {
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        Connection conexao = ControlaConexao.getConection();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            //ABRE CONEXAO COM BANCO DE DADOS

            // CRIA O COMANDO SQL PARA SELECIONAR TODOS OS DADOS DO BANCO
            String sql = "SELECT pesid, pesnome, pescpf, pesnascimento, pessexo FROM pessoa";
            //PREPARA O COMANDO PARA UMA INSTRUCAO ACEITAVEL DO SQL
            PreparedStatement instrucao = conexao.prepareStatement(sql);
            //EXECULTA E ALOCA O RESULTADO EM UM OBJETO RESULTSET
            ResultSet resultados = instrucao.executeQuery();
            while (resultados.next()) {
                Pessoa pes = new Pessoa();
                pes.setId(resultados.getInt("pesid"));
                pes.setNome(resultados.getString("pesnome"));
                System.out.println(pes.getNome());
                pes.setCpf(resultados.getString("pescpf"));

//                Calendar cal=Calendar.getInstance();
                format.format(resultados.getDate("pesnascimento"));
                pes.setNascimento(format.getCalendar());

                pes.setSexo(resultados.getString("pessexo").charAt(0));
                pessoas.add(pes);
            }
        } catch (SQLException ex) {

        } finally {
            ControlaConexao.closeConexao(conexao);
        }

        return pessoas;
    }

}
