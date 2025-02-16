package at.escapedoom.session;

import at.escapedoom.NameDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceNameController {

    @PreAuthorize("hasRole('LECTOR')")
    @GetMapping("/name")
    NameDTO serviceName() {
        return new NameDTO("Session-Api");
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping("/name1")
    NameDTO serviceName1() {
        return new NameDTO("Session-Api student");
    }
}
