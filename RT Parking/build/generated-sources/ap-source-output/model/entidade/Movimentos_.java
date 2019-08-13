package model.entidade;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entidade.Estabelecimentos;
import model.entidade.Funcionarios;
import model.entidade.Servicos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-20T19:02:24")
@StaticMetamodel(Movimentos.class)
public class Movimentos_ { 

    public static volatile SingularAttribute<Movimentos, Estabelecimentos> idestabelecimento;
    public static volatile SingularAttribute<Movimentos, Date> dhSaida;
    public static volatile SingularAttribute<Movimentos, BigDecimal> total;
    public static volatile SingularAttribute<Movimentos, Funcionarios> idfuncionario;
    public static volatile SingularAttribute<Movimentos, Date> dhEntrada;
    public static volatile SingularAttribute<Movimentos, BigDecimal> desconto;
    public static volatile SingularAttribute<Movimentos, Servicos> idservico;
    public static volatile SingularAttribute<Movimentos, Integer> idmovimento;
    public static volatile SingularAttribute<Movimentos, String> placa;

}