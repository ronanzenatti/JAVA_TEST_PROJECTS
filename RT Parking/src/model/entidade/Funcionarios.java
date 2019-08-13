/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ronan
 */
@Entity
@Table(name = "funcionarios", catalog = "parking", schema = "")
@NamedQueries({
    @NamedQuery(name = "Funcionarios.findAll", query = "SELECT f FROM Funcionarios f")
    , @NamedQuery(name = "Funcionarios.findByIdfuncionario", query = "SELECT f FROM Funcionarios f WHERE f.idfuncionario = :idfuncionario")
    , @NamedQuery(name = "Funcionarios.findByNome", query = "SELECT f FROM Funcionarios f WHERE f.nome = :nome")
    , @NamedQuery(name = "Funcionarios.findByCpf", query = "SELECT f FROM Funcionarios f WHERE f.cpf = :cpf")
    , @NamedQuery(name = "Funcionarios.findByRg", query = "SELECT f FROM Funcionarios f WHERE f.rg = :rg")
    , @NamedQuery(name = "Funcionarios.findByDtNasc", query = "SELECT f FROM Funcionarios f WHERE f.dtNasc = :dtNasc")
    , @NamedQuery(name = "Funcionarios.findByRua", query = "SELECT f FROM Funcionarios f WHERE f.rua = :rua")
    , @NamedQuery(name = "Funcionarios.findByNumero", query = "SELECT f FROM Funcionarios f WHERE f.numero = :numero")
    , @NamedQuery(name = "Funcionarios.findByBairro", query = "SELECT f FROM Funcionarios f WHERE f.bairro = :bairro")
    , @NamedQuery(name = "Funcionarios.findByCidade", query = "SELECT f FROM Funcionarios f WHERE f.cidade = :cidade")
    , @NamedQuery(name = "Funcionarios.findByTelefones", query = "SELECT f FROM Funcionarios f WHERE f.telefones = :telefones")
    , @NamedQuery(name = "Funcionarios.findByEmail", query = "SELECT f FROM Funcionarios f WHERE f.email = :email")
    , @NamedQuery(name = "Funcionarios.findBySalario", query = "SELECT f FROM Funcionarios f WHERE f.salario = :salario")
    , @NamedQuery(name = "Funcionarios.findByDtInicio", query = "SELECT f FROM Funcionarios f WHERE f.dtInicio = :dtInicio")
    , @NamedQuery(name = "Funcionarios.findByDtFim", query = "SELECT f FROM Funcionarios f WHERE f.dtFim = :dtFim")
    , @NamedQuery(name = "Funcionarios.findByUsuario", query = "SELECT f FROM Funcionarios f WHERE f.usuario = :usuario")
    , @NamedQuery(name = "Funcionarios.findBySenha", query = "SELECT f FROM Funcionarios f WHERE f.senha = :senha")
    , @NamedQuery(name = "Funcionarios.findByTipo", query = "SELECT f FROM Funcionarios f WHERE f.tipo = :tipo")})
public class Funcionarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfuncionario")
    private Integer idfuncionario;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "rg")
    private String rg;
    @Column(name = "dt_nasc")
    @Temporal(TemporalType.DATE)
    private Date dtNasc;
    @Column(name = "rua")
    private String rua;
    @Column(name = "numero")
    private String numero;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "telefones")
    private String telefones;
    @Column(name = "email")
    private String email;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario")
    private BigDecimal salario;
    @Basic(optional = false)
    @Column(name = "dt_inicio")
    @Temporal(TemporalType.DATE)
    private Date dtInicio;
    @Column(name = "dt_fim")
    @Temporal(TemporalType.DATE)
    private Date dtFim;
    @Basic(optional = false)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @Column(name = "tipo")
    private Character tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfuncionario")
    private Collection<Financeiro> financeiroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idfuncionario")
    private Collection<Movimentos> movimentosCollection;

    public Funcionarios() {
    }

    public Funcionarios(Integer idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public Funcionarios(Integer idfuncionario, String nome, Date dtInicio, String usuario, String senha, Character tipo) {
        this.idfuncionario = idfuncionario;
        this.nome = nome;
        this.dtInicio = dtInicio;
        this.usuario = usuario;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Integer getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Integer idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefones() {
        return telefones;
    }

    public void setTelefones(String telefones) {
        this.telefones = telefones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public Collection<Financeiro> getFinanceiroCollection() {
        return financeiroCollection;
    }

    public void setFinanceiroCollection(Collection<Financeiro> financeiroCollection) {
        this.financeiroCollection = financeiroCollection;
    }

    public Collection<Movimentos> getMovimentosCollection() {
        return movimentosCollection;
    }

    public void setMovimentosCollection(Collection<Movimentos> movimentosCollection) {
        this.movimentosCollection = movimentosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfuncionario != null ? idfuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionarios)) {
            return false;
        }
        Funcionarios other = (Funcionarios) object;
        if ((this.idfuncionario == null && other.idfuncionario != null) || (this.idfuncionario != null && !this.idfuncionario.equals(other.idfuncionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entidade.Funcionarios[ idfuncionario=" + idfuncionario + " ]";
    }
    
}
