package model.bean;

public class Produtos {

    private int idproduto;
    private String descricao;
    private String marca;
    private String categoria;
    private double estoque;
    private double vl_unitario;

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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    /**
     * @return the estoque
     */
    public double getEstoque() {
        return estoque;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(double estoque) {
        this.estoque = estoque;
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
}
