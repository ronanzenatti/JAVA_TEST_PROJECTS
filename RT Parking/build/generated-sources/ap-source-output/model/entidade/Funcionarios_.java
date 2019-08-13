package model.entidade;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entidade.Financeiro;
import model.entidade.Movimentos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-20T19:02:24")
@StaticMetamodel(Funcionarios.class)
public class Funcionarios_ { 

    public static volatile CollectionAttribute<Funcionarios, Movimentos> movimentosCollection;
    public static volatile SingularAttribute<Funcionarios, Integer> idfuncionario;
    public static volatile SingularAttribute<Funcionarios, String> cidade;
    public static volatile SingularAttribute<Funcionarios, Character> tipo;
    public static volatile SingularAttribute<Funcionarios, String> numero;
    public static volatile SingularAttribute<Funcionarios, String> bairro;
    public static volatile SingularAttribute<Funcionarios, BigDecimal> salario;
    public static volatile SingularAttribute<Funcionarios, String> nome;
    public static volatile SingularAttribute<Funcionarios, Date> dtInicio;
    public static volatile SingularAttribute<Funcionarios, Date> dtFim;
    public static volatile SingularAttribute<Funcionarios, String> telefones;
    public static volatile SingularAttribute<Funcionarios, String> eMail;
    public static volatile SingularAttribute<Funcionarios, String> senha;
    public static volatile SingularAttribute<Funcionarios, String> rg;
    public static volatile CollectionAttribute<Funcionarios, Financeiro> financeiroCollection;
    public static volatile SingularAttribute<Funcionarios, String> cpf;
    public static volatile SingularAttribute<Funcionarios, String> usuario;
    public static volatile SingularAttribute<Funcionarios, Date> dtNasc;
    public static volatile SingularAttribute<Funcionarios, String> rua;

}