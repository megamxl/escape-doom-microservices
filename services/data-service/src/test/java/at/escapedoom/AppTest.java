package at.escapedoom;

import at.escapedoom.data.DataApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = DataApi.class)
class AppTest {
    @Test
    void testTrue() {
        assertThat(1).isEqualTo(1);
    }
}
