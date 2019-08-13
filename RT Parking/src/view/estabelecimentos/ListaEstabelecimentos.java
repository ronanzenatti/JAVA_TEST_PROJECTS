/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.estabelecimentos;

import controller.EstabelecimentosJpaController;
import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.entidade.Estabelecimentos;

/**
 *
 * @author AVELL
 */
public class ListaEstabelecimentos extends javax.swing.JFrame {

    EntityManagerFactory objFactory;
    EntityManager manager;

    /**
     * Creates new form ListaEstabelecimentos
     */
    public ListaEstabelecimentos() {
        initComponents();

        objFactory = Persistence.createEntityManagerFactory("RT_ParkingPU");
        manager = objFactory.createEntityManager();

        DefaultTableModel modelo = (DefaultTableModel) jTableEstabelecimentos.getModel();
        jTableEstabelecimentos.setRowSorter(new TableRowSorter<TableModel>(modelo));
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();        
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);        
        jTableEstabelecimentos.getColumnModel().getColumn(0).setCellRenderer(centralizado);

        readJTable();
    }

    public void readJTable() {
        String tipo;
        DefaultTableModel modelo = (DefaultTableModel) jTableEstabelecimentos.getModel();
        modelo.setNumRows(0);

        EstabelecimentosJpaController eJPA = new EstabelecimentosJpaController(objFactory);

        for (Estabelecimentos e : eJPA.findEstabelecimentosEntities()) {
            modelo.addRow(new Object[]{
                e.getIdestabelecimento(),
                e.getNome(),
                e.getCnpj(),
                e.getCidade(),
                e.getTelefones(),
                e.getEmail()
            });
        }
    }

    public void readForFind() {
        String tipo;
        String busca = txtFind.getText();
        String campo[] = new String[8];
        DefaultTableModel modelo = (DefaultTableModel) jTableEstabelecimentos.getModel();
        modelo.setNumRows(0);
        EstabelecimentosJpaController eJPA = new EstabelecimentosJpaController(objFactory);

        campo[0] = "idestabelecimento";
        campo[1] = "nome";
        campo[2] = "cnpj";
        campo[3] = "cidade";
        campo[4] = "telefones";
        campo[5] = "email";

        for (Estabelecimentos e : eJPA.findForFind(campo[cboFind.getSelectedIndex()], busca)) {
            modelo.addRow(new Object[]{
                e.getIdestabelecimento(),
                e.getNome(),
                e.getCnpj(),
                e.getCidade(),
                e.getTelefones(),
                e.getEmail()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEstabelecimentos = new javax.swing.JTable();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        cboFind = new javax.swing.JComboBox<>();
        txtFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Listagem de Estabelecimentos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(19, 19, 19))
        );

        jTableEstabelecimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "CNPJ", "Cidade", "Telefones", "E-mail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableEstabelecimentos);
        jTableEstabelecimentos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTableEstabelecimentos.getColumnModel().getColumnCount() > 0) {
            jTableEstabelecimentos.getColumnModel().getColumn(0).setMinWidth(80);
            jTableEstabelecimentos.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTableEstabelecimentos.getColumnModel().getColumn(0).setMaxWidth(80);
            jTableEstabelecimentos.getColumnModel().getColumn(2).setMinWidth(130);
            jTableEstabelecimentos.getColumnModel().getColumn(2).setPreferredWidth(130);
            jTableEstabelecimentos.getColumnModel().getColumn(2).setMaxWidth(130);
        }

        btnCreate.setBackground(new java.awt.Color(0, 204, 51));
        btnCreate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCreate.setText("Novo");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(255, 204, 0));
        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnUpdate.setText("Alterar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setText("Excluir");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        cboFind.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cboFind.setForeground(new java.awt.Color(0, 0, 204));
        cboFind.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nome", "CNPJ", "Cidade", "Telefones", "E-mail", "Inicio", "Fim", " " }));

        txtFind.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtFind.setForeground(new java.awt.Color(0, 51, 204));

        btnFind.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFind.setText("Buscar");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
                        .addComponent(cboFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(cboFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        CadastroEstabelecimento cp = new CadastroEstabelecimento();
        cp.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (jTableEstabelecimentos.getSelectedRow() != -1 && jTableEstabelecimentos.getSelectedRowCount() == 1) {
            AlterarEstabelecimento au = new AlterarEstabelecimento((int) jTableEstabelecimentos.getValueAt(jTableEstabelecimentos.getSelectedRow(), 0));
            au.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um registro para alterar... \n"
                    + "Escolha apenas um único registro !!!", "Sistema Comercial", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (jTableEstabelecimentos.getSelectedRow() != -1 && jTableEstabelecimentos.getSelectedRowCount() == 1) {
            int option = JOptionPane.showConfirmDialog(null, "Deseja realmete excluir o registro ?", "Sistema Comercial", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                EstabelecimentosJpaController eJPA = new EstabelecimentosJpaController(objFactory);

                try {
                    if (eJPA.destroy((int) jTableEstabelecimentos.getValueAt(jTableEstabelecimentos.getSelectedRow(), 0))) {
                        readJTable();
                    }
                } catch (IllegalOrphanException ex) {
                    JOptionPane.showMessageDialog(null, "Selecione um registro para alterar... \n"
                            + "Escolha apenas um único registro !!!", "Sistema Comercial", JOptionPane.ERROR);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(ListaEstabelecimentos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um registro para alterar... \n"
                    + "Escolha apenas um único registro !!!", "Sistema Comercial", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        readForFind();
    }//GEN-LAST:event_btnFindActionPerformed

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
            java.util.logging.Logger.getLogger(ListaEstabelecimentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaEstabelecimentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaEstabelecimentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaEstabelecimentos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaEstabelecimentos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboFind;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEstabelecimentos;
    private javax.swing.JTextField txtFind;
    // End of variables declaration//GEN-END:variables
}
