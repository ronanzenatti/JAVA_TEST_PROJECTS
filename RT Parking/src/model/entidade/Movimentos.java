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
@Table(name = "movimentos", catalog = "parking", schema = "")
@NamedQueries({
    @NamedQuery(name = "Movimentos.findAll", query = "SELECT m FROM Movimentos m")
    , @NamedQuery(name = "Movimentos.findByIdmovimento", query = "SELECT m FROM Movimentos m WHERE m.idmovimento = :idmovimento")
    , @NamedQuery(name = "Movimentos.findByDhEntrada", query = "SELECT m FROM Movimentos m WHERE m.dhEntrada = :dhEntrada")
    , @NamedQuery(name = "Movimentos.findByDhSaida", query = "SELECT m FROM Movimentos m WHERE m.dhSaida = :dhSaida")
    , @NamedQuery(name = "Movimentos.findByPlaca", query = "SELECT m FROM Movimentos m WHERE m.placa = :placa")
    , @NamedQuery(name = "Movimentos.findByDesconto", query = "SELECT m FROM Movimentos m WHERE m.desconto = :desconto")
    , @NamedQuery(name = "Movimentos.findByTotal", query = "SELECT m FROM Movimentos m WHERE m.total = :total")})
public class Movimentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmovimento")
    private Integer idmovimento;
    @Basic(optional = false)
    @Column(name = "dh_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhEntrada;
    @Column(name = "dh_saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dhSaida;
    @Basic(optional = false)
    @Column(name = "placa")
    private String placa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "desconto")
    private BigDecimal desconto;
    @Column(name = "total")
    private BigDecimal total;
    @JoinColumn(name = "idestabelecimento", referencedColumnName = "idestabelecimento")
    @ManyToOne(optional = false)
    private Estabelecimentos idestabelecimento;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne(optional = false)
    private Funcionarios idfuncionario;
    @JoinColumn(name = "idservico", referencedColumnName = "idservico")
    @ManyToOne(optional = false)
    private Servicos idservico;

    public Movimentos() {
    }

    public Movimentos(Integer idmovimento) {
        this.idmovimento = idmovimento;
    }

    public Movimentos(Integer idmovimento, Date dhEntrada, String placa) {
        this.idmovimento = idmovimento;
        this.dhEntrada = dhEntrada;
        this.placa = placa;
    }

    public Integer getIdmovimento() {
        return idmovimento;
    }

    public void setIdmovimento(Integer idmovimento) {
        this.idmovimento = idmovimento;
    }

    public Date getDhEntrada() {
        return dhEntrada;
    }

    public void setDhEntrada(Date dhEntrada) {
        this.dhEntrada = dhEntrada;
    }

    public Date getDhSaida() {
        return dhSaida;
    }

    public void setDhSaida(Date dhSaida) {
        this.dhSaida = dhSaida;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public Servicos getIdservico() {
        return idservico;
    }

    public void setIdservico(Servicos idservico) {
        this.idservico = idservico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovimento != null ? idmovimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimentos)) {
            return false;
        }
        Movimentos other = (Movimentos) object;
        if ((this.idmovimento == null && other.idmovimento != null) || (this.idmovimento != null && !this.idmovimento.equals(other.idmovimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entidade.Movimentos[ idmovimento=" + idmovimento + " ]";
    }
    
}
