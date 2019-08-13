package model.bean;

import java.util.Date;

public class Movimentos {

    private int idmovimento;
    private int idpessoa;
    private char tipo;
    private Date data;
    private double desconto;
    private double vl_total;
    private String nomePessoa;

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
     * @return the idpessoa
     */
    public int getIdpessoa() {
        return idpessoa;
    }

    /**
     * @param idpessoa the idpessoa to set
     */
    public void setIdpessoa(int idpessoa) {
        this.idpessoa = idpessoa;
    }

    /**
     * @return the tipo
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the desconto
     */
    public double getDesconto() {
        return desconto;
    }

    /**
     * @param desconto the desconto to set
     */
    public void setDesconto(double desconto) {
        this.desconto = desconto;
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

    /**
     * @return the nomePessoa
     */
    public String getNomePessoa() {
        return nomePessoa;
    }

    /**
     * @param nomePessoa the nomePessoa to set
     */
    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

}
