package j4x4.repository;
import j4x4.domain.Microservice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Microservice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MicroserviceRepository extends JpaRepository<Microservice, Long> {

}
