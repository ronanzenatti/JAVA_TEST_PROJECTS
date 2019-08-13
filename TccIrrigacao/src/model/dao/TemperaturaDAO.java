/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Temperatura;

/**
 *
 * @author Aluno
 */
public class TemperaturaDAO {
    public boolean save (Temperatura t){
      Connection con = ConnectionFactory.getConnection();// Essa variável pode ser usada em qualquer outra parte da classe
        PreparedStatement stmt = null;  //pré compila o SQL e auxilia na sua criação

        boolean ret = false;

        try {
            stmt = con.prepareStatement("INSERT INTO temperatura(id_temp,valor_temp,data_hpra) VALUES (?,?,?)"); //cria o sql
            stmt.setDouble(1, t.getValor_temp());  //subistitui o ponto de interrogacao pelo nome
           
            stmt.executeUpdate();  //executa o sql ou seja,manda informacao para o banco de dados

            ret = true;

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "Sistema Irrigação", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar! \n" + ex, "Sistema Irrigação", JOptionPane.ERROR_MESSAGE);
            ret = false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt); //fecha a conexão com o BD e depois fecha o objeto Prepared Statement.
        }
        return ret;
    }
    public static void main(String[] args) {
    
        Temperatura temp = new Temperatura();
        temp.setValor_temp(23.0);
        
        TemperaturaDAO dao = new TemperaturaDAO();
       if(dao.save(temp)){
           System.out.println("Salvo com sucesso!");  
    }else{
           System.err.println("Erro ao salvar!");  
    
    
}
}
}
