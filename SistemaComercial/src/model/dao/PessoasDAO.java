package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Pessoas;

public class PessoasDAO {

    Connection con;

    public PessoasDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean create(Pessoas p) {
//        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        boolean ret = false;

        try {
            stmt = con.prepareStatement("INSERT INTO pessoas (tipo, nome, doc_fed, doc_est, insc_mun, logradouro, numero, complemento, referencia, bairro, cidade, estado, cep, email, tel_fixo, tel_com, tel_cel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            stmt.setString(1, String.valueOf(p.getTipo()));
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getDoc_fed());
            stmt.setString(4, p.getDoc_est());
            stmt.setInt(5, p.getInsc_mun());
            stmt.setString(6, p.getLogradouro());
            stmt.setString(7, p.getNumero());
            stmt.setString(8, p.getComplemento());
            stmt.setString(9, p.getReferencia());
            stmt.setString(10, p.getBairro());
            stmt.setString(11, p.getCidade());
            stmt.setString(12, p.getEstado());
            stmt.setString(13, p.getCep());
            stmt.setString(14, p.getEmail());
            stmt.setString(15, p.getTel_fixo());
            stmt.setString(16, p.getTel_com());
            stmt.setString(17, p.getTel_cel());

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

    public boolean update(Pessoas p) {
//        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        boolean ret = false;

        try {
            stmt = con.prepareStatement("UPDATE pessoas SET tipo = ?, nome = ?, doc_fed = ?, doc_est = ?, insc_mun = ?, logradouro = ?, numero = ?, complemento = ?, referencia = ?, bairro = ?, cidade = ?, estado = ?, cep = ?, email = ?, tel_fixo = ?, tel_com = ?, tel_cel = ? WHERE idpessoa = ?");
            stmt.setString(1, String.valueOf(p.getTipo()));
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getDoc_fed());
            stmt.setString(4, p.getDoc_est());
            stmt.setInt(5, p.getInsc_mun());
            stmt.setString(6, p.getLogradouro());
            stmt.setString(7, p.getNumero());
            stmt.setString(8, p.getComplemento());
            stmt.setString(9, p.getReferencia());
            stmt.setString(10, p.getBairro());
            stmt.setString(11, p.getCidade());
            stmt.setString(12, p.getEstado());
            stmt.setString(13, p.getCep());
            stmt.setString(14, p.getEmail());
            stmt.setString(15, p.getTel_fixo());
            stmt.setString(16, p.getTel_com());
            stmt.setString(17, p.getTel_cel());
            stmt.setInt(18, p.getIdpessoa());

            System.out.println(stmt.toString());

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

    public boolean delete(int idputo) {
//        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        boolean ret = false;

        try {
            stmt = con.prepareStatement("DELETE FROM pessoas WHERE idpessoa = ?");
            stmt.setInt(1, idputo);

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

    public List<Pessoas> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Pessoas> list = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM pessoas");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Pessoas p = new Pessoas();

                p.setIdpessoa(rs.getInt("idpessoa"));
                p.setTipo(rs.getString("tipo").charAt(0));
                p.setNome(rs.getString("nome"));
                p.setDoc_fed(rs.getString("doc_fed"));
                p.setDoc_est(rs.getString("doc_est"));
                p.setInsc_mun(rs.getInt("insc_mun"));
                p.setLogradouro(rs.getString("logradouro"));
                p.setNumero(rs.getString("numero"));
                p.setComplemento(rs.getString("complemento"));
                p.setReferencia(rs.getString("referencia"));
                p.setBairro(rs.getString("bairro"));
                p.setCidade(rs.getString("cidade"));
                p.setEstado(rs.getString("estado"));
                p.setCep(rs.getString("cep"));
                p.setEmail(rs.getString("email"));
                p.setTel_fixo(rs.getString("tel_fixo"));
                p.setTel_com(rs.getString("tel_com"));
                p.setTel_cel(rs.getString("tel_cel"));

                list.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar os Dados!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return list;
    }

    public List<Pessoas> readForFind(String campo, String busca) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Pessoas> list = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM pessoas WHERE " + campo + " LIKE ?");
            stmt.setString(1, "%" + busca + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Pessoas p = new Pessoas();

                p.setIdpessoa(rs.getInt("idpessoa"));
                p.setTipo(rs.getString("tipo").charAt(0));
                p.setNome(rs.getString("nome"));
                p.setDoc_fed(rs.getString("doc_fed"));
                p.setDoc_est(rs.getString("doc_est"));
                p.setInsc_mun(rs.getInt("insc_mun"));
                p.setLogradouro(rs.getString("logradouro"));
                p.setNumero(rs.getString("numero"));
                p.setComplemento(rs.getString("complemento"));
                p.setReferencia(rs.getString("referencia"));
                p.setBairro(rs.getString("bairro"));
                p.setCidade(rs.getString("cidade"));
                p.setEstado(rs.getString("estado"));
                p.setCep(rs.getString("cep"));
                p.setEmail(rs.getString("email"));
                p.setTel_fixo(rs.getString("tel_fixo"));
                p.setTel_com(rs.getString("tel_com"));
                p.setTel_cel(rs.getString("tel_cel"));
                list.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar os Dados!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return list;
    }

    public Pessoas findById(int idputo) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pessoas p = new Pessoas();

        try {
            stmt = con.prepareStatement("SELECT * FROM pessoas WHERE idpessoa = ?");
            stmt.setInt(1, idputo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                p.setIdpessoa(rs.getInt("idpessoa"));
                p.setTipo(rs.getString("tipo").charAt(0));
                p.setNome(rs.getString("nome"));
                p.setDoc_fed(rs.getString("doc_fed"));
                p.setDoc_est(rs.getString("doc_est"));
                p.setInsc_mun(rs.getInt("insc_mun"));
                p.setLogradouro(rs.getString("logradouro"));
                p.setNumero(rs.getString("numero"));
                p.setComplemento(rs.getString("complemento"));
                p.setReferencia(rs.getString("referencia"));
                p.setBairro(rs.getString("bairro"));
                p.setCidade(rs.getString("cidade"));
                p.setEstado(rs.getString("estado"));
                p.setCep(rs.getString("cep"));
                p.setEmail(rs.getString("email"));
                p.setTel_fixo(rs.getString("tel_fixo"));
                p.setTel_com(rs.getString("tel_com"));
                p.setTel_cel(rs.getString("tel_cel"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar os Dados!!! \n" + ex, "Sistema Comercial", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return p;
    }
}
