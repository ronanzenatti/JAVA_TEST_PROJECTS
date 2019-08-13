package model.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entidade.Clientes;
import model.entidade.Estabelecimentos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-20T19:02:24")
@StaticMetamodel(Convenios.class)
public class Convenios_ { 

    public static volatile SingularAttribute<Convenios, Estabelecimentos> idestabelecimento;
    public static volatile SingularAttribute<Convenios, Integer> idconvenio;
    public static volatile SingularAttribute<Convenios, Character> tipoPagamento;
    public static volatile SingularAttribute<Convenios, Date> dtInicio;
    public static volatile SingularAttribute<Convenios, Date> dtFim;
    public static volatile SingularAttribute<Convenios, Clientes> idcliente;
    public static volatile SingularAttribute<Convenios, Integer> idservico;
    public static volatile SingularAttribute<Convenios, String> descricao;

}