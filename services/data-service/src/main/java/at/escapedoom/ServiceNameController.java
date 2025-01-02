package at.escapedoom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceNameController {

    @GetMapping("/name")
    NameDTO serviceName() {
        return new NameDTO("Data-Api");
    }
}
