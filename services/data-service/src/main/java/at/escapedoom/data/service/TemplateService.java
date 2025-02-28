package at.escapedoom.data.service;

import at.escapedoom.data.TemplateNotFoundException;
import at.escapedoom.data.data.entity.EscapeRoomTemplate;
import at.escapedoom.data.data.reporitory.EscapeRoomTemplateRepository;
import at.escapedoom.data.rest.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TemplateService {

    private final EscapeRoomTemplateRepository repository;

    public TemplateService(EscapeRoomTemplateRepository repository) {
        this.repository = repository;
    }

    public EscapeRoomTemplateResult createTemplate(EscapeRoomTemplateCreateRequest request) {
        log.debug("Creating template with name: {}", request.getName());

        EscapeRoomTemplate template = EscapeRoomTemplate.builder().name(request.getName())
                .description(request.getDescription()).build();

        repository.save(template);

        return EscapeRoomTemplateResult.builder().message("Template created successfully").build();
    }

    public EscapeRoomTemplateResult deleteTemplate(String escapeRoomTemplateId) throws TemplateNotFoundException {
        log.debug("Deleting template with ID: {}", escapeRoomTemplateId);

        UUID id = validateUUID(escapeRoomTemplateId);

        if (!repository.existsById(id)) {
            throw new TemplateNotFoundException("Template not found");
        }

        repository.deleteById(id);
        log.debug("Template with ID {} successfully deleted", escapeRoomTemplateId);

        return EscapeRoomTemplateResult.builder().message("Template deleted successfully").build();
    }

    public List<EscapeRoomTemplateDTO> getAllTemplates() {
        log.debug("Fetching all templates");

        return repository.findAll().stream()
                .map(template -> EscapeRoomTemplateDTO.builder()
                        .escapeRoomTemplateId(template.getEscapeRoomTemplateID().toString()).name(template.getName())
                        .description(template.getDescription()).build())
                .toList();
    }

    public at.escapedoom.data.rest.model.EscapeRoomTemplate getTemplate(String escapeRoomTemplateId)
            throws TemplateNotFoundException {
        UUID id = validateUUID(escapeRoomTemplateId);

        EscapeRoomTemplate entity = repository.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException("Template not found"));

        return toApiModel(entity);
    }

    private at.escapedoom.data.rest.model.EscapeRoomTemplate toApiModel(EscapeRoomTemplate entity) {
        if (entity == null)
            return null;

        at.escapedoom.data.rest.model.EscapeRoomTemplate apiTemplate = new at.escapedoom.data.rest.model.EscapeRoomTemplate();
        apiTemplate.setEscapeRoomTemplateId(entity.getEscapeRoomTemplateID().toString());
        apiTemplate.setName(entity.getName());
        apiTemplate.setDescription(entity.getDescription());

        return apiTemplate;
    }

    public EscapeRoomTemplateUpdateResult updateTemplate(String escapeRoomTemplateId,
            EscapeRoomTemplateUpdateRequest request) throws TemplateNotFoundException {
        log.debug("Updating template with ID: {}", escapeRoomTemplateId);

        UUID id = validateUUID(escapeRoomTemplateId);

        EscapeRoomTemplate template = repository.findById(id)
                .orElseThrow(() -> new TemplateNotFoundException("Template not found"));

        template.setName(request.getName());
        template.setDescription(request.getDescription());

        repository.save(template);

        return EscapeRoomTemplateUpdateResult.builder().message("Template updated successfully").build();
    }

    private UUID validateUUID(String escapeRoomTemplateId) {
        try {
            return UUID.fromString(escapeRoomTemplateId);
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format: {}", escapeRoomTemplateId);
            throw new IllegalArgumentException("Invalid UUID format");
        }
    }
}
