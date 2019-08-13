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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Usuarios;

/**
 *
 * @author ronan
 */
public class UsuariosDAO {

    Connection con;

    public UsuariosDAO() {
        con = ConnectionFactory.getConnection();
    }

    public int create(Usuarios u) {
//        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        int id = 0;

        try {
            stmt = con.prepareStatement("INSERT INTO usuarios (nome, usuario, senha, perfil) VALUES (?, ?, ?, ?)");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getUsuario());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, String.valueOf(u.getPerfil()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(0);
            }

            JOptionPane.showMessageDialog(null, "Salvo com Sucesso!!!", "Sistema Comercial", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
            id = 0;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return id;
    }

    public boolean update(Usuarios u) {
//        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        boolean ret = false;

        try {
            stmt = con.prepareStatement("UPDATE usuarios SET nome = ? , usuario = ?, senha = ?, perfil = ? WHERE idusuario = ?");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getUsuario());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, String.valueOf(u.getPerfil()));
            stmt.setInt(5, u.getIdusuario());

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

    public boolean delete(int idusuario) {
//        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        boolean ret = false;

        try {
            stmt = con.prepareStatement("DELETE FROM usuarios WHERE idusuario = ?");
            stmt.setInt(1, idusuario);

            stmt.executeUpdate();

            ret = true;

            JOptionPane.showMessageDialog(null, "Deletado com Sucesso!!!", "Sistema Comercial", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Deletar!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
            ret = false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return ret;
    }

    public List<Usuarios> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuarios> list = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuarios");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuarios usr = new Usuarios();

                usr.setIdusuario(rs.getInt("idusuario"));
                usr.setNome(rs.getString("nome"));
                usr.setUsuario(rs.getString("usuario"));
                usr.setSenha(rs.getString("senha"));
                usr.setPerfil(rs.getString("perfil").charAt(0));

                list.add(usr);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar os Dados!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return list;
    }

    public List<Usuarios> readForFind(String campo, String busca) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuarios> list = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuarios WHERE " + campo + " LIKE ?");
            stmt.setString(1, "%" + busca + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuarios usr = new Usuarios();

                usr.setIdusuario(rs.getInt("idusuario"));
                usr.setNome(rs.getString("nome"));
                usr.setUsuario(rs.getString("usuario"));
                usr.setSenha(rs.getString("senha"));
                usr.setPerfil(rs.getString("perfil").charAt(0));

                list.add(usr);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar os Dados!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return list;
    }

    public Usuarios findById(int idusuario) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuarios usr = new Usuarios();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuarios WHERE idusuario = ?");
            stmt.setInt(1, idusuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                usr.setIdusuario(rs.getInt("idusuario"));
                usr.setNome(rs.getString("nome"));
                usr.setUsuario(rs.getString("usuario"));
                usr.setSenha(rs.getString("senha"));
                usr.setPerfil(rs.getString("perfil").charAt(0));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar os Dados!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return usr;
    }

    public boolean updateSenha(Usuarios u) {
//        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        boolean ret = false;

        try {
            stmt = con.prepareStatement("UPDATE usuarios SET senha = ? WHERE idusuario = ?");
            stmt.setString(1, u.getSenha());
            stmt.setInt(2, u.getIdusuario());

            stmt.executeUpdate();

            ret = true;

            JOptionPane.showMessageDialog(null, "Senha Atualizada com Sucesso!!!", "Sistema Comercial", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
            ret = false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return ret;
    }

    public Usuarios login(String usuario) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuarios usr = new Usuarios();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuarios WHERE usuario = ? LIMIT 1");
            stmt.setString(1, usuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                usr.setIdusuario(rs.getInt("idusuario"));
                usr.setNome(rs.getString("nome"));
                usr.setUsuario(rs.getString("usuario"));
                usr.setSenha(rs.getString("senha"));
                usr.setPerfil(rs.getString("perfil").charAt(0));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar os Dados!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return usr;
    }

}
