package at.escapedoom.data.service;

import at.escapedoom.data.data.TemplateRepository;
import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.Template;
import at.escapedoom.data.rest.model.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static at.escapedoom.data.utils.KeyCloakUtils.getUserId;

@Slf4j
@Service
public class TemplateService {

    private final TemplateRepository repository;
    private final RiddleService riddleService;
    private final SceneService sceneService;

    public TemplateService(TemplateRepository repository, RiddleService riddleService, SceneService sceneService) {
        this.repository = repository;
        this.riddleService = riddleService;
        this.sceneService = sceneService;
    }

    public EscapeRoomTemplateDTO createTemplate(@NonNull EscapeRoomTemplateCreateRequestDTO request) {
        log.debug("Creating template with name: {}", request.getName());

        Template template = Template.builder().name(request.getName()).userId(getUserId())
                .description(request.getDescription()).build();

        repository.save(template);

        return toApiModel(template);
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

        return repository.findAllByUserId(getUserId()).stream()
                .map(template -> EscapeRoomTemplateDTO.builder()
                        .userId(template.getUserId().toString())
                        .templateId(template.getEscapeRoomTemplateID().toString()).name(template.getName())
                        .description(template.getDescription())
                        .levels(template.getEscapeRoomLevels().stream().map(this::toApiModel).toList())
                        .name(template.getName())
                        .build())
                .toList();
    }

    public EscapeRoomTemplateDTO getTemplate(String escapeRoomTemplateId) {
        UUID id = validateUUID(escapeRoomTemplateId);

        Optional<Template> entityOpt = repository.findById(id);
        assert entityOpt.isPresent();
        return toApiModel(entityOpt.get());
    }

    private EscapeRoomTemplateDTO toApiModel(Template entity) {
        if (entity == null)
            return null;

        EscapeRoomTemplateDTO apiTemplate = new EscapeRoomTemplateDTO();
        apiTemplate.templateId(entity.getEscapeRoomTemplateID().toString());
        apiTemplate.setName(entity.getName());
        apiTemplate.setDescription(entity.getDescription());
        apiTemplate.setUserId(entity.getUserId().toString());

        return apiTemplate;
    }

    // FIXME: This only updates Name or Description
    public EscapeRoomTemplateUpdateResultDTO updateTemplate(String escapeRoomTemplateId,
            EscapeRoomTemplateUpdateRequestDTO request) {
        log.debug("Updating template with ID: {}", escapeRoomTemplateId);

        UUID id = validateUUID(escapeRoomTemplateId);

        Template template = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Template with ID " + escapeRoomTemplateId + " not found"));

        template.setName(request.getName());
        template.setDescription(request.getDescription());

        repository.save(template);
        return EscapeRoomTemplateUpdateResultDTO.builder().message("Template updated successfully").build();
    }

    private UUID validateUUID(String escapeRoomTemplateId) {
        try {
            return UUID.fromString(escapeRoomTemplateId);
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format: {}", escapeRoomTemplateId);
            throw new IllegalArgumentException("Invalid UUID format");
        }
    }

    private EscapeRoomLevelDTO toApiModel(Level entity) {
        return EscapeRoomLevelDTO.builder()
                .templateId(entity.getTemplateId().toString())
                .levelId(entity.getLevelId().toString())
                .riddle(riddleService.toRestBody(entity.getRiddle()))
                .sequence(entity.getLevelSequence())
                .scenes(entity.getScenes().stream().map(sceneService::toScene).toList())
                .build();
    }
}
