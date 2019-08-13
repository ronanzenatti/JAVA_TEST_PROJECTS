/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "convenios", catalog = "parking", schema = "")
@NamedQueries({
    @NamedQuery(name = "Convenios.findAll", query = "SELECT c FROM Convenios c")
    , @NamedQuery(name = "Convenios.findByIdconvenio", query = "SELECT c FROM Convenios c WHERE c.idconvenio = :idconvenio")
    , @NamedQuery(name = "Convenios.findByDescricao", query = "SELECT c FROM Convenios c WHERE c.descricao = :descricao")
    , @NamedQuery(name = "Convenios.findByIdservico", query = "SELECT c FROM Convenios c WHERE c.idservico = :idservico")
    , @NamedQuery(name = "Convenios.findByTipoPagamento", query = "SELECT c FROM Convenios c WHERE c.tipoPagamento = :tipoPagamento")
    , @NamedQuery(name = "Convenios.findByDtInicio", query = "SELECT c FROM Convenios c WHERE c.dtInicio = :dtInicio")
    , @NamedQuery(name = "Convenios.findByDtFim", query = "SELECT c FROM Convenios c WHERE c.dtFim = :dtFim")})
public class Convenios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconvenio")
    private Integer idconvenio;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "idservico")
    private Integer idservico;
    @Basic(optional = false)
    @Column(name = "tipo_pagamento")
    private Character tipoPagamento;
    @Basic(optional = false)
    @Column(name = "dt_inicio")
    @Temporal(TemporalType.DATE)
    private Date dtInicio;
    @Column(name = "dt_fim")
    @Temporal(TemporalType.DATE)
    private Date dtFim;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Clientes idcliente;
    @JoinColumn(name = "idestabelecimento", referencedColumnName = "idestabelecimento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Estabelecimentos idestabelecimento;

    public Convenios() {
    }

    public Convenios(Integer idconvenio) {
        this.idconvenio = idconvenio;
    }

    public Convenios(Integer idconvenio, String descricao, Character tipoPagamento, Date dtInicio) {
        this.idconvenio = idconvenio;
        this.descricao = descricao;
        this.tipoPagamento = tipoPagamento;
        this.dtInicio = dtInicio;
    }

    public Integer getIdconvenio() {
        return idconvenio;
    }

    public void setIdconvenio(Integer idconvenio) {
        this.idconvenio = idconvenio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdservico() {
        return idservico;
    }

    public void setIdservico(Integer idservico) {
        this.idservico = idservico;
    }

    public Character getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(Character tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    public Clientes getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Clientes idcliente) {
        this.idcliente = idcliente;
    }

    public Estabelecimentos getIdestabelecimento() {
        return idestabelecimento;
    }

    public void setIdestabelecimento(Estabelecimentos idestabelecimento) {
        this.idestabelecimento = idestabelecimento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconvenio != null ? idconvenio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Convenios)) {
            return false;
        }
        Convenios other = (Convenios) object;
        if ((this.idconvenio == null && other.idconvenio != null) || (this.idconvenio != null && !this.idconvenio.equals(other.idconvenio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entidade.Convenios[ idconvenio=" + idconvenio + " ]";
    }
    
}
