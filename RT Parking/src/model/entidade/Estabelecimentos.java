/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidade;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "estabelecimentos", catalog = "parking", schema = "")
@NamedQueries({
    @NamedQuery(name = "Estabelecimentos.findAll", query = "SELECT e FROM Estabelecimentos e")
    , @NamedQuery(name = "Estabelecimentos.findByIdestabelecimento", query = "SELECT e FROM Estabelecimentos e WHERE e.idestabelecimento = :idestabelecimento")
    , @NamedQuery(name = "Estabelecimentos.findByNome", query = "SELECT e FROM Estabelecimentos e WHERE e.nome = :nome")
    , @NamedQuery(name = "Estabelecimentos.findByCnpj", query = "SELECT e FROM Estabelecimentos e WHERE e.cnpj = :cnpj")
    , @NamedQuery(name = "Estabelecimentos.findByRua", query = "SELECT e FROM Estabelecimentos e WHERE e.rua = :rua")
    , @NamedQuery(name = "Estabelecimentos.findByNumero", query = "SELECT e FROM Estabelecimentos e WHERE e.numero = :numero")
    , @NamedQuery(name = "Estabelecimentos.findByComplemento", query = "SELECT e FROM Estabelecimentos e WHERE e.complemento = :complemento")
    , @NamedQuery(name = "Estabelecimentos.findByBairro", query = "SELECT e FROM Estabelecimentos e WHERE e.bairro = :bairro")
    , @NamedQuery(name = "Estabelecimentos.findByCidade", query = "SELECT e FROM Estabelecimentos e WHERE e.cidade = :cidade")
    , @NamedQuery(name = "Estabelecimentos.findByTelefones", query = "SELECT e FROM Estabelecimentos e WHERE e.telefones = :telefones")
    , @NamedQuery(name = "Estabelecimentos.findByEmail", query = "SELECT e FROM Estabelecimentos e WHERE e.email = :email")
    , @NamedQuery(name = "Estabelecimentos.findByDtInicio", query = "SELECT e FROM Estabelecimentos e WHERE e.dtInicio = :dtInicio")
    , @NamedQuery(name = "Estabelecimentos.findByDtFim", query = "SELECT e FROM Estabelecimentos e WHERE e.dtFim = :dtFim")
    , @NamedQuery(name = "Estabelecimentos.findByVagasCarro", query = "SELECT e FROM Estabelecimentos e WHERE e.vagasCarro = :vagasCarro")
    , @NamedQuery(name = "Estabelecimentos.findByVagasMoto", query = "SELECT e FROM Estabelecimentos e WHERE e.vagasMoto = :vagasMoto")})
public class Estabelecimentos implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idestabelecimento", fetch = FetchType.EAGER)
    private Collection<Servicos> servicosCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestabelecimento")
    private Integer idestabelecimento;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name = "rua")
    private String rua;
    @Column(name = "numero")
    private String numero;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "telefones")
    private String telefones;
    @Column(name = "email")
    private String email;
    @Column(name = "dt_inicio")
    @Temporal(TemporalType.DATE)
    private Date dtInicio;
    @Column(name = "dt_fim")
    @Temporal(TemporalType.DATE)
    private Date dtFim;
    @Column(name = "vagas_carro")
    private Integer vagasCarro;
    @Column(name = "vagas_moto")
    private Integer vagasMoto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idestabelecimento")
    private Collection<Convenios> conveniosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idestabelecimento")
    private Collection<Financeiro> financeiroCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idestabelecimento")
    private Collection<Movimentos> movimentosCollection;

    public Estabelecimentos() {
    }

    public Estabelecimentos(Integer idestabelecimento) {
        this.idestabelecimento = idestabelecimento;
    }

    public Estabelecimentos(Integer idestabelecimento, String nome) {
        this.idestabelecimento = idestabelecimento;
        this.nome = nome;
    }

    public Integer getIdestabelecimento() {
        return idestabelecimento;
    }

    public void setIdestabelecimento(Integer idestabelecimento) {
        this.idestabelecimento = idestabelecimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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

    public Integer getVagasCarro() {
        return vagasCarro;
    }

    public void setVagasCarro(Integer vagasCarro) {
        this.vagasCarro = vagasCarro;
    }

    public Integer getVagasMoto() {
        return vagasMoto;
    }

    public void setVagasMoto(Integer vagasMoto) {
        this.vagasMoto = vagasMoto;
    }

    public Collection<Convenios> getConveniosCollection() {
        return conveniosCollection;
    }

    public void setConveniosCollection(Collection<Convenios> conveniosCollection) {
        this.conveniosCollection = conveniosCollection;
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
        hash += (idestabelecimento != null ? idestabelecimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estabelecimentos)) {
            return false;
        }
        Estabelecimentos other = (Estabelecimentos) object;
        if ((this.idestabelecimento == null && other.idestabelecimento != null) || (this.idestabelecimento != null && !this.idestabelecimento.equals(other.idestabelecimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.entidade.Estabelecimentos[ idestabelecimento=" + idestabelecimento + " ]";
    }

    public Collection<Servicos> getServicosCollection() {
        return servicosCollection;
    }

    public void setServicosCollection(Collection<Servicos> servicosCollection) {
        this.servicosCollection = servicosCollection;
    }
    
}
