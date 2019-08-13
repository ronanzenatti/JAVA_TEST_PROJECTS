package model.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entidade.Convenios;
import model.entidade.Financeiro;
import model.entidade.Movimentos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-20T19:02:24")
@StaticMetamodel(Estabelecimentos.class)
public class Estabelecimentos_ { 

    public static volatile CollectionAttribute<Estabelecimentos, Movimentos> movimentosCollection;
    public static volatile SingularAttribute<Estabelecimentos, String> cidade;
    public static volatile SingularAttribute<Estabelecimentos, String> numero;
    public static volatile SingularAttribute<Estabelecimentos, String> bairro;
    public static volatile SingularAttribute<Estabelecimentos, String> nome;
    public static volatile CollectionAttribute<Estabelecimentos, Convenios> conveniosCollection;
    public static volatile SingularAttribute<Estabelecimentos, String> cnpj;
    public static volatile SingularAttribute<Estabelecimentos, Date> dtInicio;
    public static volatile SingularAttribute<Estabelecimentos, Date> dtFim;
    public static volatile SingularAttribute<Estabelecimentos, String> telefones;
    public static volatile SingularAttribute<Estabelecimentos, String> eMail;
    public static volatile SingularAttribute<Estabelecimentos, Integer> idestabelecimento;
    public static volatile SingularAttribute<Estabelecimentos, String> complemento;
    public static volatile SingularAttribute<Estabelecimentos, Integer> vagasMoto;
    public static volatile CollectionAttribute<Estabelecimentos, Financeiro> financeiroCollection;
    public static volatile SingularAttribute<Estabelecimentos, Integer> vagasCarro;
    public static volatile SingularAttribute<Estabelecimentos, String> rua;

}