package model.entidade;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entidade.Estabelecimentos;
import model.entidade.Funcionarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-20T19:02:24")
@StaticMetamodel(Financeiro.class)
public class Financeiro_ { 

    public static volatile SingularAttribute<Financeiro, Character> formaPagamento;
    public static volatile SingularAttribute<Financeiro, Estabelecimentos> idestabelecimento;
    public static volatile SingularAttribute<Financeiro, BigDecimal> total;
    public static volatile SingularAttribute<Financeiro, Funcionarios> idfuncionario;
    public static volatile SingularAttribute<Financeiro, Character> tipoLancamento;
    public static volatile SingularAttribute<Financeiro, Date> dhLancamento;
    public static volatile SingularAttribute<Financeiro, Integer> numeroRecibo;
    public static volatile SingularAttribute<Financeiro, Integer> idfinanceiro;
    public static volatile SingularAttribute<Financeiro, Integer> idmovimento;

}