package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Produtos;

public class ProdutosDAO {

    Connection con;

    public ProdutosDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean create(Produtos p) {
//        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        boolean ret = false;

        try {
            stmt = con.prepareStatement("INSERT INTO produtos (descricao, marca, categoria, estoque, vl_unitario) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, p.getDescricao());
            stmt.setString(2, p.getMarca());
            stmt.setString(3, p.getCategoria());
            stmt.setDouble(4, p.getEstoque());
            stmt.setDouble(5, p.getVl_unitario());

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

    public boolean update(Produtos p) {
//        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        boolean ret = false;

        try {
            stmt = con.prepareStatement("UPDATE produtos SET descricao = ? , marca = ?, categoria = ?, estoque = ?, vl_unitario = ? WHERE idproduto = ?");
            stmt.setString(1, p.getDescricao());
            stmt.setString(2, p.getMarca());
            stmt.setString(3, p.getCategoria());
            stmt.setDouble(4, p.getEstoque());
            stmt.setDouble(5, p.getVl_unitario());
            stmt.setInt(6, p.getIdproduto());

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

    public boolean delete(int idproduto) {
//        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        boolean ret = false;

        try {
            stmt = con.prepareStatement("DELETE FROM produtos WHERE idproduto = ?");
            stmt.setInt(1, idproduto);

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

    public List<Produtos> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produtos> list = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produtos prod = new Produtos();

                prod.setIdproduto(rs.getInt("idproduto"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setMarca(rs.getString("marca"));
                prod.setCategoria(rs.getString("categoria"));
                prod.setEstoque(rs.getDouble("estoque"));
                prod.setVl_unitario(rs.getDouble("vl_unitario"));

                list.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar os Dados!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return list;
    }

    public List<Produtos> readForFind(String campo, String busca) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Produtos> list = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE " + campo + " LIKE ?");
            stmt.setString(1, "%" + busca + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produtos prod = new Produtos();

                prod.setIdproduto(rs.getInt("idproduto"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setMarca(rs.getString("marca"));
                prod.setCategoria(rs.getString("categoria"));
                prod.setEstoque(rs.getDouble("estoque"));
                prod.setVl_unitario(rs.getDouble("vl_unitario"));

                list.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar os Dados!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return list;
    }

    public Produtos findById(int idproduto) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produtos prod = new Produtos();

        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE idproduto = ?");
            stmt.setInt(1, idproduto);
            rs = stmt.executeQuery();

            while (rs.next()) {
                prod.setIdproduto(rs.getInt("idproduto"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setMarca(rs.getString("marca"));
                prod.setCategoria(rs.getString("categoria"));
                prod.setEstoque(rs.getDouble("estoque"));
                prod.setVl_unitario(rs.getDouble("vl_unitario"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar os Dados!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return prod;
    }
}
