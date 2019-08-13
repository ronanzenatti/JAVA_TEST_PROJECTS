package model.entidade;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entidade.Movimentos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-20T19:02:24")
@StaticMetamodel(Servicos.class)
public class Servicos_ { 

    public static volatile SingularAttribute<Servicos, Character> tipoCobranca;
    public static volatile CollectionAttribute<Servicos, Movimentos> movimentosCollection;
    public static volatile SingularAttribute<Servicos, Integer> idestabelecimento;
    public static volatile SingularAttribute<Servicos, BigDecimal> valor;
    public static volatile SingularAttribute<Servicos, Character> tipoPeriodo;
    public static volatile SingularAttribute<Servicos, Integer> quantidade;
    public static volatile SingularAttribute<Servicos, Integer> idservico;
    public static volatile SingularAttribute<Servicos, String> descricao;

}