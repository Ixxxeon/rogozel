package j4x4.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Catalog.class)
public abstract class Catalog_ {

	public static volatile SingularAttribute<Catalog, String> name;
	public static volatile SingularAttribute<Catalog, Long> id;
	public static volatile SetAttribute<Catalog, Microservice> microservices;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String MICROSERVICES = "microservices";

}

