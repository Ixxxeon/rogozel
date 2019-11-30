package j4x4.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Location.class)
public abstract class Location_ {

	public static volatile SingularAttribute<Location, String> streetAddress;
	public static volatile SingularAttribute<Location, String> city;
	public static volatile SingularAttribute<Location, String> flat;
	public static volatile SingularAttribute<Location, Long> id;
	public static volatile SingularAttribute<Location, String> house;

	public static final String STREET_ADDRESS = "streetAddress";
	public static final String CITY = "city";
	public static final String FLAT = "flat";
	public static final String ID = "id";
	public static final String HOUSE = "house";

}

