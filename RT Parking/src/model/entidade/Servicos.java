/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author ronan
 */
@Entity
@Table(name = "servicos", catalog = "parking", schema = "")
@NamedQueries({
    @NamedQuery(name = "Servicos.findAll", query = "SELECT s FROM Servicos s")
    , @NamedQuery(name = "Servicos.findByIdservico", query = "SELECT s FROM Servicos s WHERE s.idservico = :idservico")
    , @NamedQuery(name = "Servicos.findByDescricao", query = "SELECT s FROM Servicos s WHERE s.descricao = :descricao")
    , @NamedQuery(name = "Servicos.findByTipoCobranca", query = "SELECT s FROM Servicos s WHERE s.tipoCobranca = :tipoCobranca")
    , @NamedQuery(name = "Servicos.findByQuantidade", query = "SELECT s FROM Servicos s WHERE s.quantidade = :quantidade")
    , @NamedQuery(name = "Servicos.findByValor", query = "SELECT s FROM Servicos s WHERE s.valor = :valor")})
public class Servicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idservico")
    private Integer idservico;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "tipo_cobranca")
    private Character tipoCobranca;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private int quantidade;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor")
    private BigDecimal valor;
    @JoinColumn(name = "idestabelecimento", referencedColumnName = "idestabelecimento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Estabelecimentos idestabelecimento;

    public Servicos() {
    }

    public Servicos(Integer idservico) {
        this.idservico = idservico;
    }

    public Servicos(Integer idservico, String descricao, Character tipoCobranca, int quantidade, BigDecimal valor) {
        this.idservico = idservico;
        this.descricao = descricao;
        this.tipoCobranca = tipoCobranca;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Integer getIdservico() {
        return idservico;
    }

    public void setIdservico(Integer idservico) {
        this.idservico = idservico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Character getTipoCobranca() {
        return tipoCobranca;
    }

    public void setTipoCobranca(Character tipoCobranca) {
        this.tipoCobranca = tipoCobranca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
        hash += (idservico != null ? idservico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicos)) {
            return false;
        }
        Servicos other = (Servicos) object;
        if ((this.idservico == null && other.idservico != null) || (this.idservico != null && !this.idservico.equals(other.idservico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entidade.Servicos[ idservico=" + idservico + " ]";
    }
    
}
