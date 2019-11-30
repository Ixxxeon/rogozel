package j4x4.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import j4x4.web.rest.TestUtil;

public class MicroserviceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Microservice.class);
        Microservice microservice1 = new Microservice();
        microservice1.setId(1L);
        Microservice microservice2 = new Microservice();
        microservice2.setId(microservice1.getId());
        assertThat(microservice1).isEqualTo(microservice2);
        microservice2.setId(2L);
        assertThat(microservice1).isNotEqualTo(microservice2);
        microservice1.setId(null);
        assertThat(microservice1).isNotEqualTo(microservice2);
    }
}
