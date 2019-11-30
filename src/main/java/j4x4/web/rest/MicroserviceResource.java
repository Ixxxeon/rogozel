package j4x4.web.rest;

import j4x4.domain.Microservice;
import j4x4.service.MicroserviceService;
import j4x4.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link j4x4.domain.Microservice}.
 */
@RestController
@RequestMapping("/api")
public class MicroserviceResource {

    private final Logger log = LoggerFactory.getLogger(MicroserviceResource.class);

    private static final String ENTITY_NAME = "microservice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MicroserviceService microserviceService;

    public MicroserviceResource(MicroserviceService microserviceService) {
        this.microserviceService = microserviceService;
    }

    /**
     * {@code POST  /microservices} : Create a new microservice.
     *
     * @param microservice the microservice to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new microservice, or with status {@code 400 (Bad Request)} if the microservice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/microservices")
    public ResponseEntity<Microservice> createMicroservice(@RequestBody Microservice microservice) throws URISyntaxException {
        log.debug("REST request to save Microservice : {}", microservice);
        if (microservice.getId() != null) {
            throw new BadRequestAlertException("A new microservice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Microservice result = microserviceService.save(microservice);
        return ResponseEntity.created(new URI("/api/microservices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /microservices} : Updates an existing microservice.
     *
     * @param microservice the microservice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated microservice,
     * or with status {@code 400 (Bad Request)} if the microservice is not valid,
     * or with status {@code 500 (Internal Server Error)} if the microservice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/microservices")
    public ResponseEntity<Microservice> updateMicroservice(@RequestBody Microservice microservice) throws URISyntaxException {
        log.debug("REST request to update Microservice : {}", microservice);
        if (microservice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Microservice result = microserviceService.save(microservice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, microservice.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /microservices} : get all the microservices.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of microservices in body.
     */
    @GetMapping("/microservices")
    public ResponseEntity<List<Microservice>> getAllMicroservices(Pageable pageable) {
        log.debug("REST request to get a page of Microservices");
        Page<Microservice> page = microserviceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /microservices/:id} : get the "id" microservice.
     *
     * @param id the id of the microservice to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the microservice, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/microservices/{id}")
    public ResponseEntity<Microservice> getMicroservice(@PathVariable Long id) {
        log.debug("REST request to get Microservice : {}", id);
        Optional<Microservice> microservice = microserviceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(microservice);
    }

    /**
     * {@code DELETE  /microservices/:id} : delete the "id" microservice.
     *
     * @param id the id of the microservice to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/microservices/{id}")
    public ResponseEntity<Void> deleteMicroservice(@PathVariable Long id) {
        log.debug("REST request to delete Microservice : {}", id);
        microserviceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
