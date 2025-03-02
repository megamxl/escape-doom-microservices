package at.escapedoom.data.service;

import at.escapedoom.data.data.TemplateRepository;
import at.escapedoom.data.data.entity.EscapeRoomTemplate;
import at.escapedoom.data.rest.model.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@PreAuthorize("hasRole('LECTOR')")
public class TemplateService {

    private final TemplateRepository repository;

    public TemplateService(TemplateRepository repository) {
        this.repository = repository;
    }

    public EscapeRoomTemplateResultDTO createTemplate(EscapeRoomTemplateCreateRequestDTO request) {
        log.debug("Creating template with name: {}", request.getName());

        EscapeRoomTemplate template = EscapeRoomTemplate.builder().name(request.getName())
                .description(request.getDescription()).build();

        repository.save(template);

        return EscapeRoomTemplateResultDTO.builder()
                .message("Template created successfully. Id: " + template.getEscapeRoomTemplateID().toString()).build();
    }

    public EscapeRoomTemplateResultDTO deleteTemplate(String escapeRoomTemplateId) {
        log.debug("Deleting template with ID: {}", escapeRoomTemplateId);

        UUID id = validateUUID(escapeRoomTemplateId);

        if (!repository.existsById(id)) {
            return EscapeRoomTemplateResultDTO.builder().message("Template not found").build();
        }

        repository.deleteById(id);
        log.debug("Template with ID {} successfully deleted", escapeRoomTemplateId);

        return EscapeRoomTemplateResultDTO.builder().message("Template deleted successfully").build();
    }

    public List<EscapeRoomTemplateDTO> getAllTemplates() {
        log.debug("Fetching all templates");

        return repository.findAll().stream()
                .map(template -> EscapeRoomTemplateDTO.builder()
                        .escapeRoomTemplateId(template.getEscapeRoomTemplateID().toString()).name(template.getName())
                        .description(template.getDescription()).build())
                .toList();
    }

    public EscapeRoomTemplateDTO getTemplate(String escapeRoomTemplateId) {
        UUID id = validateUUID(escapeRoomTemplateId);

        Optional<EscapeRoomTemplate> entityOpt = repository.findById(id);
        assert entityOpt.isPresent();
        return toApiModel(entityOpt.get());
    }

    private EscapeRoomTemplateDTO toApiModel(EscapeRoomTemplate entity) {
        if (entity == null)
            return null;

        EscapeRoomTemplateDTO apiTemplate = new EscapeRoomTemplateDTO();
        apiTemplate.setEscapeRoomTemplateId(entity.getEscapeRoomTemplateID().toString());
        apiTemplate.setName(entity.getName());
        apiTemplate.setDescription(entity.getDescription());

        return apiTemplate;
    }

    public EscapeRoomTemplateUpdateResultDTO updateTemplate(String escapeRoomTemplateId,
            EscapeRoomTemplateUpdateRequestDTO request) {
        log.debug("Updating template with ID: {}", escapeRoomTemplateId);

        UUID id = validateUUID(escapeRoomTemplateId);

        Optional<EscapeRoomTemplate> templateOpt = repository.findById(id);

        if (templateOpt.isPresent()) {
            EscapeRoomTemplate template = templateOpt.get();

            template.setName(request.getName());
            template.setDescription(request.getDescription());

            repository.save(template);

            return EscapeRoomTemplateUpdateResultDTO.builder().message("Template updated successfully").build();
        } else {
            throw new EntityNotFoundException("Template with ID " + escapeRoomTemplateId + " not found");
        }
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
