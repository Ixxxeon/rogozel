package j4x4.domain;

import j4x4.domain.enumeration.Status;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Microservice.class)
public abstract class Microservice_ {

	public static volatile SingularAttribute<Microservice, Catalog> catalog;
	public static volatile SingularAttribute<Microservice, String> name;
	public static volatile SingularAttribute<Microservice, Long> id;
	public static volatile SingularAttribute<Microservice, Status> status;

	public static final String CATALOG = "catalog";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String STATUS = "status";

}

