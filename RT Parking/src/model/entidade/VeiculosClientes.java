/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidade;

import java.io.Serializable;
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
@Table(name = "veiculos_clientes", catalog = "parking", schema = "")
@NamedQueries({
    @NamedQuery(name = "VeiculosClientes.findAll", query = "SELECT v FROM VeiculosClientes v")
    , @NamedQuery(name = "VeiculosClientes.findByIdvc", query = "SELECT v FROM VeiculosClientes v WHERE v.idvc = :idvc")
    , @NamedQuery(name = "VeiculosClientes.findByPlaca", query = "SELECT v FROM VeiculosClientes v WHERE v.placa = :placa")
    , @NamedQuery(name = "VeiculosClientes.findByVeiculo", query = "SELECT v FROM VeiculosClientes v WHERE v.veiculo = :veiculo")})
public class VeiculosClientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvc")
    private Integer idvc;
    @Basic(optional = false)
    @Column(name = "placa")
    private String placa;
    @Basic(optional = false)
    @Column(name = "veiculo")
    private String veiculo;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Clientes idcliente;

    public VeiculosClientes() {
    }

    public VeiculosClientes(Integer idvc) {
        this.idvc = idvc;
    }

    public VeiculosClientes(Integer idvc, String placa, String veiculo) {
        this.idvc = idvc;
        this.placa = placa;
        this.veiculo = veiculo;
    }

    public Integer getIdvc() {
        return idvc;
    }

    public void setIdvc(Integer idvc) {
        this.idvc = idvc;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public Clientes getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Clientes idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvc != null ? idvc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VeiculosClientes)) {
            return false;
        }
        VeiculosClientes other = (VeiculosClientes) object;
        if ((this.idvc == null && other.idvc != null) || (this.idvc != null && !this.idvc.equals(other.idvc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entidade.VeiculosClientes[ idvc=" + idvc + " ]";
    }
    
}
