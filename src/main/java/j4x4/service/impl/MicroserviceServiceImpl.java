package j4x4.service.impl;

import j4x4.service.MicroserviceService;
import j4x4.domain.Microservice;
import j4x4.repository.MicroserviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Microservice}.
 */
@Service
@Transactional
public class MicroserviceServiceImpl implements MicroserviceService {

    private final Logger log = LoggerFactory.getLogger(MicroserviceServiceImpl.class);

    private final MicroserviceRepository microserviceRepository;

    public MicroserviceServiceImpl(MicroserviceRepository microserviceRepository) {
        this.microserviceRepository = microserviceRepository;
    }

    /**
     * Save a microservice.
     *
     * @param microservice the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Microservice save(Microservice microservice) {
        log.debug("Request to save Microservice : {}", microservice);
        return microserviceRepository.save(microservice);
    }

    /**
     * Get all the microservices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Microservice> findAll(Pageable pageable) {
        log.debug("Request to get all Microservices");
        return microserviceRepository.findAll(pageable);
    }


    /**
     * Get one microservice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Microservice> findOne(Long id) {
        log.debug("Request to get Microservice : {}", id);
        return microserviceRepository.findById(id);
    }

    /**
     * Delete the microservice by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Microservice : {}", id);
        microserviceRepository.deleteById(id);
    }
}
