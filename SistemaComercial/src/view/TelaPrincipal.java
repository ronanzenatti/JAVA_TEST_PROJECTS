/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import model.bean.Usuarios;
import view.pessoas.ListaPessoas;
import view.produtos.ListaProdutos;
import view.usuario.ListaUsuarios;
import view.usuario.RedefinirSenha;

/**
 *
 * @author ronan
 */
public class TelaPrincipal extends javax.swing.JFrame {

    public Usuarios usr;

    /**
     * Creates new form TelaPrincipal
     *
     * @param uLogin
     */
    public TelaPrincipal(Usuarios uLogin) {
        initComponents();
        // coloca uma figura na barra de título da janela
        URL url = this.getClass().getResource("/img/store.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
        usr = uLogin;
        lblUsuario.setText("Seja bem vindo " + usr.getNome());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mnuUsuarios = new javax.swing.JMenuItem();
        mnuPessoas = new javax.swing.JMenuItem();
        mnuProdutos = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnuSair = new javax.swing.JMenu();
        mnuTrocaSenha = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Comercial");

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUsuario.setText("Nome do Usuário");

        jMenu1.setText("Cadastros");

        mnuUsuarios.setText("Usuários");
        mnuUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuUsuariosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuUsuarios);

        mnuPessoas.setText("Pessoas");
        mnuPessoas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPessoasActionPerformed(evt);
            }
        });
        jMenu1.add(mnuPessoas);

        mnuProdutos.setText("Produtos");
        mnuProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProdutosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuProdutos);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Movimentação");

        jMenuItem4.setText("Entrada de Produtos");
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Venda");
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Relatórios");
        jMenuBar1.add(jMenu3);

        mnuSair.setText("Sistema");

        mnuTrocaSenha.setText("Trocar Senha");
        mnuTrocaSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTrocaSenhaActionPerformed(evt);
            }
        });
        mnuSair.add(mnuTrocaSenha);

        jMenuItem7.setText("Sair");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        mnuSair.add(jMenuItem7);

        jMenuBar1.add(mnuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(710, Short.MAX_VALUE)
                .addComponent(lblUsuario)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsuario)
                .addContainerGap(460, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mnuUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuUsuariosActionPerformed
        ListaUsuarios tela = new ListaUsuarios();
        tela.setVisible(true);
    }//GEN-LAST:event_mnuUsuariosActionPerformed

    private void mnuTrocaSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTrocaSenhaActionPerformed
        RedefinirSenha rs = new RedefinirSenha(usr);
        rs.setVisible(true);
    }//GEN-LAST:event_mnuTrocaSenhaActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void mnuPessoasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPessoasActionPerformed
        ListaPessoas tela = new ListaPessoas();
        tela.setVisible(true);
    }//GEN-LAST:event_mnuPessoasActionPerformed

    private void mnuProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProdutosActionPerformed
        ListaProdutos tela = new ListaProdutos();
        tela.setVisible(true);
    }//GEN-LAST:event_mnuProdutosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal(new Usuarios()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JMenuItem mnuPessoas;
    private javax.swing.JMenuItem mnuProdutos;
    private javax.swing.JMenu mnuSair;
    private javax.swing.JMenuItem mnuTrocaSenha;
    private javax.swing.JMenuItem mnuUsuarios;
    // End of variables declaration//GEN-END:variables
}