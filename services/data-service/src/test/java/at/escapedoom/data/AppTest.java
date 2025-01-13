package at.escapedoom.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AppTest {
    @Test
    void testTrue() {
        assertThat(1).isEqualTo(1);
    }
}
