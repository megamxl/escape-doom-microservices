package at.escapedoom.data.service;

import at.escapedoom.data.data.entity.EscapeRoomTemplate;
import at.escapedoom.data.data.reporitory.EscapeRoomTemplateRepository;
import at.escapedoom.data.rest.api.TemplateApiDelegate;
import at.escapedoom.data.rest.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class TemplateApiDelegateImpl implements TemplateApiDelegate {

    private final EscapeRoomTemplateRepository repository;

    public TemplateApiDelegateImpl(EscapeRoomTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<EscapeRoomTemplateResult> createTemplate(EscapeRoomTemplateCreateRequest request) {
        log.debug("Creating template with name: {}", request.getName());

        EscapeRoomTemplate template = EscapeRoomTemplate.builder().name(request.getName())
                .description(request.getDescription()).build();

        repository.save(template);

        EscapeRoomTemplateResult result = EscapeRoomTemplateResult.builder().message("Template created successfully")
                .build();

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EscapeRoomTemplateResult> deleteTemplate(String escapeRoomTemplateId) {
        log.info("Deleting template with ID: {}", escapeRoomTemplateId);

        UUID id;
        try {
            id = UUID.fromString(escapeRoomTemplateId);
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format: {}", escapeRoomTemplateId);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(EscapeRoomTemplateResult.builder().message("Invalid UUID format").build());
        }

        if (!repository.existsById(id)) {
            log.warn("Template with ID {} not found", escapeRoomTemplateId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(EscapeRoomTemplateResult.builder().message("Template not found").build());
        }

        repository.deleteById(id);
        log.info("Template with ID {} successfully deleted", escapeRoomTemplateId);

        return ResponseEntity.ok(EscapeRoomTemplateResult.builder().message("Template deleted successfully").build());
    }

    @Override
    public ResponseEntity<List<EscapeRoomTemplateDTO>> getAllTemplates() {
        log.info("Fetching all templates");

        List<EscapeRoomTemplateDTO> templates = repository.findAll().stream()
                .map(template -> EscapeRoomTemplateDTO.builder()
                        .escapeRoomTemplateId(template.getEscapeRoomTemplateID().toString()).name(template.getName())
                        .description(template.getDescription()).build())
                .toList();

        return ResponseEntity.ok(templates);
    }

    @Override
    public ResponseEntity<at.escapedoom.data.rest.model.EscapeRoomTemplate> getTemplate(String escapeRoomTemplateId) {
        Optional<EscapeRoomTemplate> templateEntity = repository.findById(UUID.fromString(escapeRoomTemplateId));

        return templateEntity.map(entity -> {
            at.escapedoom.data.rest.model.EscapeRoomTemplate apiTemplate = new at.escapedoom.data.rest.model.EscapeRoomTemplate();
            apiTemplate.setEscapeRoomTemplateId(String.valueOf(entity.getEscapeRoomTemplateID()));
            apiTemplate.setName(entity.getName());
            apiTemplate.setDescription(entity.getDescription());

            return ResponseEntity.ok(apiTemplate);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<EscapeRoomTemplateUpdateResult> putTemplate(String escapeRoomTemplateId,
            EscapeRoomTemplateUpdateRequest request) {

        log.info("Updating template with ID: {}", escapeRoomTemplateId);

        UUID id;
        try {
            id = UUID.fromString(escapeRoomTemplateId);
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format: {}", escapeRoomTemplateId);
            return new ResponseEntity<>(EscapeRoomTemplateUpdateResult.builder().message("Invalid UUID format").build(),
                    HttpStatus.BAD_REQUEST);
        }

        Optional<EscapeRoomTemplate> existingTemplate = repository.findById(id);
        if (existingTemplate.isEmpty()) {
            log.warn("Template with ID {} not found", escapeRoomTemplateId);
            return new ResponseEntity<>(EscapeRoomTemplateUpdateResult.builder().message("Template not found").build(),
                    HttpStatus.NOT_FOUND);
        }

        EscapeRoomTemplate updatedTemplate = existingTemplate.get();
        updatedTemplate.setName(request.getName());
        updatedTemplate.setDescription(request.getDescription());
        repository.save(updatedTemplate);

        return ResponseEntity
                .ok(EscapeRoomTemplateUpdateResult.builder().message("Template updated successfully").build());
    }

}
