package model.entidade;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.entidade.Convenios;
import model.entidade.VeiculosClientes;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-20T19:02:24")
@StaticMetamodel(Clientes.class)
public class Clientes_ { 

    public static volatile CollectionAttribute<Clientes, VeiculosClientes> veiculosClientesCollection;
    public static volatile SingularAttribute<Clientes, String> cidade;
    public static volatile SingularAttribute<Clientes, String> docFed;
    public static volatile SingularAttribute<Clientes, String> numero;
    public static volatile SingularAttribute<Clientes, String> bairro;
    public static volatile SingularAttribute<Clientes, String> nome;
    public static volatile CollectionAttribute<Clientes, Convenios> conveniosCollection;
    public static volatile SingularAttribute<Clientes, Integer> idcliente;
    public static volatile SingularAttribute<Clientes, String> telefones;
    public static volatile SingularAttribute<Clientes, String> eMail;
    public static volatile SingularAttribute<Clientes, Date> dtNasc;
    public static volatile SingularAttribute<Clientes, String> rua;

}