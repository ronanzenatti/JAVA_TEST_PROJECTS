/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Movimentos;

/**
 *
 * @author ronan
 */
public class MovimentosDAO {

    Connection con;
    DateFormat dateEU;
    DateFormat dateBR;

    public MovimentosDAO() {
        con = ConnectionFactory.getConnection();
        dateEU = new SimpleDateFormat("yyyy-MM-dd");
        dateBR = new SimpleDateFormat("dd/MM/yyyy");
    }

    public boolean create(Movimentos m) {
//        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        boolean ret = false;

        try {
            stmt = con.prepareStatement("INSERT INTO movimentos (tipo, data, idpessoa, desconto, vl_total) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, String.valueOf(m.getTipo()));
            stmt.setString(2, dateEU.format(m.getData()));
            stmt.setInt(3, m.getIdpessoa());
            stmt.setDouble(4, m.getDesconto());
            stmt.setDouble(5, m.getVl_total());

            stmt.executeUpdate();

            ret = true;

            JOptionPane.showMessageDialog(null, "Salvo com Sucesso!!!", "Sistema Comercial", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
            ret = false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return ret;
    }

    public boolean update(Movimentos m) {
//        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        boolean ret = false;

        try {
            stmt = con.prepareStatement("UPDATE movimentos SET tipo = ? , data = ?, desconto = ?, estoque = ?, vl_total = ? WHERE idmovimento = ?");
            stmt.setString(1, String.valueOf(m.getTipo()));
            stmt.setString(2, dateEU.format(m.getData()));
            stmt.setInt(3, m.getIdpessoa());
            stmt.setDouble(4, m.getDesconto());
            stmt.setDouble(5, m.getVl_total());
            stmt.setInt(6, m.getIdmovimento());

            stmt.executeUpdate();

            ret = true;

            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso!!!", "Sistema Comercial", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
            ret = false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return ret;
    }

    public List<Movimentos> read(char tipo) throws ParseException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Movimentos> list = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT m.*, p.nome FROM movimentos m "
                    + "INNER JOIN pessoas p ON (m.idpessoa = p.idpessoa) "
                    + "WHERE m.tipo = ?");
            stmt.setString(1, String.valueOf(tipo));
            rs = stmt.executeQuery();

            while (rs.next()) {

                Movimentos mov = new Movimentos();

                mov.setIdmovimento(rs.getInt("idmovimento"));
                mov.setData((Date) dateBR.parse(rs.getString("data")));
                mov.setNomePessoa(rs.getString("nome"));
                mov.setDesconto(rs.getDouble("desconto"));
                mov.setVl_total(rs.getDouble("vl_total"));

                list.add(mov);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar os Dados!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
            System.err.println(ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return list;
    }

    public Movimentos findById(int idmovimento) throws ParseException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Movimentos mov = new Movimentos();

        try {
            stmt = con.prepareStatement("SELECT m.*, p.nome FROM movimentos AS m "
                    + "INNER JOIN pessoas AS p ON (m.idpessoa = p.idpessoa) "
                    + "WHERE m.idmovimento = ?");            
            stmt.setInt(1, idmovimento);
            rs = stmt.executeQuery();

            while (rs.next()) {
                mov.setIdmovimento(rs.getInt("idmovimento"));
                mov.setData((Date) dateBR.parse(rs.getString("data")));
                mov.setNomePessoa(rs.getString("nome"));
                mov.setDesconto(rs.getDouble("desconto"));
                mov.setVl_total(rs.getDouble("vl_total"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar os Dados!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return mov;
    }
}
