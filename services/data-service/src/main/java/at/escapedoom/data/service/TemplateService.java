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

    public TemplateDTO createTemplate(@NonNull TemplateCreateRequestDTO request) {
        log.debug("Creating template with name: {}", request.getName());

        Template template = Template.builder().name(request.getName()).userId(getUserId())
                .description(request.getDescription()).build();

        repository.save(template);

        return toApiModel(template);
    }

    public TemplateResultDTO deleteTemplate(String TemplateId) {
        log.debug("Deleting template with ID: {}", TemplateId);

        UUID id = validateUUID(TemplateId);

        if (!repository.existsById(id)) {
            return TemplateResultDTO.builder().message("Template not found").build();
        }

        repository.deleteById(id);
        log.debug("Template with ID {} successfully deleted", TemplateId);

        return TemplateResultDTO.builder().message("Template deleted successfully").build();
    }

    public List<TemplateDTO> getAllTemplates() {
        log.debug("Fetching all templates");

        return repository.findAllByUserId(getUserId()).stream()
                .map(template -> TemplateDTO.builder().userId(template.getUserId().toString())
                        .templateId(template.getTemplateId().toString()).name(template.getName())
                        .description(template.getDescription())
                        .levels(template.getLevel().stream().map(this::toApiModel).toList()).name(template.getName())
                        .build())
                .toList();
    }

    public TemplateDTO getTemplate(String TemplateId) {
        UUID id = validateUUID(TemplateId);

        Optional<Template> entityOpt = repository.findById(id);
        assert entityOpt.isPresent();
        return toApiModel(entityOpt.get());
    }

    private TemplateDTO toApiModel(Template entity) {
        if (entity == null)
            return null;

        TemplateDTO apiTemplate = new TemplateDTO();
        apiTemplate.templateId(entity.getTemplateId().toString());
        apiTemplate.setName(entity.getName());
        apiTemplate.setDescription(entity.getDescription());
        apiTemplate.setUserId(entity.getUserId().toString());

        return apiTemplate;
    }

    // FIXME: This only updates Name or Description
    public TemplateUpdateResultDTO updateTemplate(String TemplateId, TemplateUpdateRequestDTO request) {
        log.debug("Updating template with ID: {}", TemplateId);

        UUID id = validateUUID(TemplateId);

        Template template = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Template with ID " + TemplateId + " not found"));

        template.setName(request.getName());
        template.setDescription(request.getDescription());

        repository.save(template);
        return TemplateUpdateResultDTO.builder().message("Template updated successfully").build();
    }

    private UUID validateUUID(String TemplateId) {
        try {
            return UUID.fromString(TemplateId);
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format: {}", TemplateId);
            throw new IllegalArgumentException("Invalid UUID format");
        }
    }

    private LevelDTO toApiModel(Level entity) {
        return LevelDTO.builder().templateId(entity.getTemplateId().toString()).levelId(entity.getLevelId().toString())
                .riddle(riddleService.toRestBody(entity.getRiddle())).levelSequence(entity.getLevelSequence())
                .scenes(entity.getScenes().stream().map(sceneService::toSceneDTO).toList()).build();
    }
}
