package j4x4.service;

import j4x4.domain.Microservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Microservice}.
 */
public interface MicroserviceService {

    /**
     * Save a microservice.
     *
     * @param microservice the entity to save.
     * @return the persisted entity.
     */
    Microservice save(Microservice microservice);

    /**
     * Get all the microservices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Microservice> findAll(Pageable pageable);


    /**
     * Get the "id" microservice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Microservice> findOne(Long id);

    /**
     * Delete the "id" microservice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
