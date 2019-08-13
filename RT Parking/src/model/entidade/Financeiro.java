/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ronan
 */
@Entity
@Table(name = "financeiro", catalog = "parking", schema = "")
@NamedQueries({
    @NamedQuery(name = "Financeiro.findAll", query = "SELECT f FROM Financeiro f")
    , @NamedQuery(name = "Financeiro.findByIdfinanceiro", query = "SELECT f FROM Financeiro f WHERE f.idfinanceiro = :idfinanceiro")
    , @NamedQuery(name = "Financeiro.findByDhLancamento", query = "SELECT f FROM Financeiro f WHERE f.dhLancamento = :dhLancamento")
    , @NamedQuery(name = "Financeiro.findByTipoLancamento", query = "SELECT f FROM Financeiro f WHERE f.tipoLancamento = :tipoLancamento")
    , @NamedQuery(name = "Financeiro.findByFormaPagamento", query = "SELECT f FROM Financeiro f WHERE f.formaPagamento = :formaPagamento")
    , @NamedQuery(name = "Financeiro.findByIdmovimento", query = "SELECT f FROM Financeiro f WHERE f.idmovimento = :idmovimento")
    , @NamedQuery(name = "Financeiro.findByTotal", query = "SELECT f FROM Financeiro f WHERE f.total = :total")
    , @NamedQuery(name = "Financeiro.findByNumeroRecibo", query = "SELECT f FROM Financeiro f WHERE f.numeroRecibo = :numeroRecibo")})
public class Financeiro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfinanceiro")
    private Integer idfinanceiro;
    @Basic(optional = false)
    @Column(name = "dh_lancamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhLancamento;
    @Basic(optional = false)
    @Column(name = "tipo_lancamento")
    private Character tipoLancamento;
    @Column(name = "forma_pagamento")
    private Character formaPagamento;
    @Column(name = "idmovimento")
    private Integer idmovimento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "total")
    private BigDecimal total;
    @Column(name = "numero_recibo")
    private Integer numeroRecibo;
    @JoinColumn(name = "idestabelecimento", referencedColumnName = "idestabelecimento")
    @ManyToOne(optional = false)
    private Estabelecimentos idestabelecimento;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne(optional = false)
    private Funcionarios idfuncionario;

    public Financeiro() {
    }

    public Financeiro(Integer idfinanceiro) {
        this.idfinanceiro = idfinanceiro;
    }

    public Financeiro(Integer idfinanceiro, Date dhLancamento, Character tipoLancamento, BigDecimal total) {
        this.idfinanceiro = idfinanceiro;
        this.dhLancamento = dhLancamento;
        this.tipoLancamento = tipoLancamento;
        this.total = total;
    }

    public Integer getIdfinanceiro() {
        return idfinanceiro;
    }

    public void setIdfinanceiro(Integer idfinanceiro) {
        this.idfinanceiro = idfinanceiro;
    }

    public Date getDhLancamento() {
        return dhLancamento;
    }

    public void setDhLancamento(Date dhLancamento) {
        this.dhLancamento = dhLancamento;
    }

    public Character getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(Character tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public Character getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Character formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Integer getIdmovimento() {
        return idmovimento;
    }

    public void setIdmovimento(Integer idmovimento) {
        this.idmovimento = idmovimento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getNumeroRecibo() {
        return numeroRecibo;
    }

    public void setNumeroRecibo(Integer numeroRecibo) {
        this.numeroRecibo = numeroRecibo;
    }

    public Estabelecimentos getIdestabelecimento() {
        return idestabelecimento;
    }

    public void setIdestabelecimento(Estabelecimentos idestabelecimento) {
        this.idestabelecimento = idestabelecimento;
    }

    public Funcionarios getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Funcionarios idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfinanceiro != null ? idfinanceiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Financeiro)) {
            return false;
        }
        Financeiro other = (Financeiro) object;
        if ((this.idfinanceiro == null && other.idfinanceiro != null) || (this.idfinanceiro != null && !this.idfinanceiro.equals(other.idfinanceiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entidade.Financeiro[ idfinanceiro=" + idfinanceiro + " ]";
    }
    
}
