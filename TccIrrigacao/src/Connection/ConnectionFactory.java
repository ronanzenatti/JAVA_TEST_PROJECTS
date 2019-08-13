/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class ConnectionFactory { //criando a classe

    //privado para que somente essa variavel só possa ser usada dentro da própria classe
    //as variaveis são staticas pois os metodos são staticos
    //a variavel é final pq é carregada na memoria ao iniciar a classe
    //a variavel driver define qual driver jdbc iremos utilizar para conexao
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    //a URL é o caminho para chegar ao banco de dados
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/bdirrigacao";

   //para entrar em um banco de dados é necessario ter um usuario e uma senha
    private static final String USER = "root";
    private static final String PASS = "";

    //cria a conexão com o banco de dados e retorna um objeto do tipo conexão
    public static Connection getConnection() {
        try {//tentar
            Class.forName(DRIVER);//carrega a classe JDBC
            return DriverManager.getConnection(URL, USER, PASS);//TRATA OS DRIVERS DA CLASSE CLASS
        } catch (SQLException ex) { //trata os erros da classe DriverManager
            throw new RuntimeException("Erro na conexão...", ex);
        } catch (ClassNotFoundException ex) {//verifica o erro do carregamento da classe driver ou JDBC
            throw new RuntimeException("Erro na conexão...", ex);
        }
    }

    public static void closeConnection(Connection con) {//é o metodo que encerra a conexao com o banco de dados
        try {
            if (con != null) {//verifica se nao tem conexão para poder fechar
                con.close();
            }
        } catch (SQLException ex) {//se caso houver ele fecha a conexão
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static void closeConnection(Connection con, PreparedStatement stmt) { //fecha a conexão e o Prepared Statement
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}




