package j4x4.web.rest;

import j4x4.RogozelApp;
import j4x4.domain.Microservice;
import j4x4.repository.MicroserviceRepository;
import j4x4.service.MicroserviceService;
import j4x4.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static j4x4.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import j4x4.domain.enumeration.Status;
/**
 * Integration tests for the {@link MicroserviceResource} REST controller.
 */
@SpringBootTest(classes = RogozelApp.class)
public class MicroserviceResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.NEW;
    private static final Status UPDATED_STATUS = Status.INPROCESS;

    @Autowired
    private MicroserviceRepository microserviceRepository;

    @Autowired
    private MicroserviceService microserviceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMicroserviceMockMvc;

    private Microservice microservice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MicroserviceResource microserviceResource = new MicroserviceResource(microserviceService);
        this.restMicroserviceMockMvc = MockMvcBuilders.standaloneSetup(microserviceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Microservice createEntity(EntityManager em) {
        Microservice microservice = new Microservice()
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS);
        return microservice;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Microservice createUpdatedEntity(EntityManager em) {
        Microservice microservice = new Microservice()
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);
        return microservice;
    }

    @BeforeEach
    public void initTest() {
        microservice = createEntity(em);
    }

    @Test
    @Transactional
    public void createMicroservice() throws Exception {
        int databaseSizeBeforeCreate = microserviceRepository.findAll().size();

        // Create the Microservice
        restMicroserviceMockMvc.perform(post("/api/microservices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(microservice)))
            .andExpect(status().isCreated());

        // Validate the Microservice in the database
        List<Microservice> microserviceList = microserviceRepository.findAll();
        assertThat(microserviceList).hasSize(databaseSizeBeforeCreate + 1);
        Microservice testMicroservice = microserviceList.get(microserviceList.size() - 1);
        assertThat(testMicroservice.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMicroservice.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createMicroserviceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = microserviceRepository.findAll().size();

        // Create the Microservice with an existing ID
        microservice.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMicroserviceMockMvc.perform(post("/api/microservices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(microservice)))
            .andExpect(status().isBadRequest());

        // Validate the Microservice in the database
        List<Microservice> microserviceList = microserviceRepository.findAll();
        assertThat(microserviceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMicroservices() throws Exception {
        // Initialize the database
        microserviceRepository.saveAndFlush(microservice);

        // Get all the microserviceList
        restMicroserviceMockMvc.perform(get("/api/microservices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(microservice.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getMicroservice() throws Exception {
        // Initialize the database
        microserviceRepository.saveAndFlush(microservice);

        // Get the microservice
        restMicroserviceMockMvc.perform(get("/api/microservices/{id}", microservice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(microservice.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMicroservice() throws Exception {
        // Get the microservice
        restMicroserviceMockMvc.perform(get("/api/microservices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMicroservice() throws Exception {
        // Initialize the database
        microserviceService.save(microservice);

        int databaseSizeBeforeUpdate = microserviceRepository.findAll().size();

        // Update the microservice
        Microservice updatedMicroservice = microserviceRepository.findById(microservice.getId()).get();
        // Disconnect from session so that the updates on updatedMicroservice are not directly saved in db
        em.detach(updatedMicroservice);
        updatedMicroservice
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS);

        restMicroserviceMockMvc.perform(put("/api/microservices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMicroservice)))
            .andExpect(status().isOk());

        // Validate the Microservice in the database
        List<Microservice> microserviceList = microserviceRepository.findAll();
        assertThat(microserviceList).hasSize(databaseSizeBeforeUpdate);
        Microservice testMicroservice = microserviceList.get(microserviceList.size() - 1);
        assertThat(testMicroservice.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMicroservice.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingMicroservice() throws Exception {
        int databaseSizeBeforeUpdate = microserviceRepository.findAll().size();

        // Create the Microservice

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMicroserviceMockMvc.perform(put("/api/microservices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(microservice)))
            .andExpect(status().isBadRequest());

        // Validate the Microservice in the database
        List<Microservice> microserviceList = microserviceRepository.findAll();
        assertThat(microserviceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMicroservice() throws Exception {
        // Initialize the database
        microserviceService.save(microservice);

        int databaseSizeBeforeDelete = microserviceRepository.findAll().size();

        // Delete the microservice
        restMicroserviceMockMvc.perform(delete("/api/microservices/{id}", microservice.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Microservice> microserviceList = microserviceRepository.findAll();
        assertThat(microserviceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
