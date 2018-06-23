/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import util.DataUtil;

/**
 *
 * @author Aluno
 */
public class PessoaTableModel extends AbstractTableModel {

    Pessoa pessoa = new Pessoa();
    private List<Pessoa> pessoas;

    public PessoaTableModel(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public int getRowCount() {
        return pessoas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Pessoa p = pessoas.get(linha);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        switch(coluna){
            case 0:
                return p.getNome();
            case 1:
                return p.getCpf();
            case 2:
                if(p.getSexo() == 'M'){
                    return "Masculino";
                } else if(p.getSexo() == 'F'){
                    return "Feminino";
                };
            case 3:
                return DataUtil.ConverterDataEmTexto(p.getNascimento());
                
        }
        return null;
    }
    
    @Override
    public String getColumnName(int coluna){
        switch (coluna) {
            case 0:
                return "Nome";
            case 1:
                return "CPF";
            case 2:
                return "Sexo";
            case 3:
                return "Nascimento";
        }
        return null;
    }
}
