package model.bean;

public class ItensMovimento {

    private int iditem;
    private int idproduto;
    private int idmovimento;
    private double qtde;
    private double vl_unitario;
    private double vl_total;

    /**
     * @return the iditem
     */
    public int getIditem() {
        return iditem;
    }

    /**
     * @param iditem the iditem to set
     */
    public void setIditem(int iditem) {
        this.iditem = iditem;
    }

    /**
     * @return the idproduto
     */
    public int getIdproduto() {
        return idproduto;
    }

    /**
     * @param idproduto the idproduto to set
     */
    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    /**
     * @return the idmovimento
     */
    public int getIdmovimento() {
        return idmovimento;
    }

    /**
     * @param idmovimento the idmovimento to set
     */
    public void setIdmovimento(int idmovimento) {
        this.idmovimento = idmovimento;
    }

    /**
     * @return the qtde
     */
    public double getQtde() {
        return qtde;
    }

    /**
     * @param qtde the qtde to set
     */
    public void setQtde(double qtde) {
        this.qtde = qtde;
    }

    /**
     * @return the vl_unitario
     */
    public double getVl_unitario() {
        return vl_unitario;
    }

    /**
     * @param vl_unitario the vl_unitario to set
     */
    public void setVl_unitario(double vl_unitario) {
        this.vl_unitario = vl_unitario;
    }

    /**
     * @return the vl_total
     */
    public double getVl_total() {
        return vl_total;
    }

    /**
     * @param vl_total the vl_total to set
     */
    public void setVl_total(double vl_total) {
        this.vl_total = vl_total;
    }
}
