package j4x4.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Catalog.
 */
@Entity
@Table(name = "catalog")
public class Catalog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "catalog")
    private Set<Microservice> microservices = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Catalog name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Microservice> getMicroservices() {
        return microservices;
    }

    public Catalog microservices(Set<Microservice> microservices) {
        this.microservices = microservices;
        return this;
    }

    public Catalog addMicroservice(Microservice microservice) {
        this.microservices.add(microservice);
        microservice.setCatalog(this);
        return this;
    }

    public Catalog removeMicroservice(Microservice microservice) {
        this.microservices.remove(microservice);
        microservice.setCatalog(null);
        return this;
    }

    public void setMicroservices(Set<Microservice> microservices) {
        this.microservices = microservices;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Catalog)) {
            return false;
        }
        return id != null && id.equals(((Catalog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Catalog{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
