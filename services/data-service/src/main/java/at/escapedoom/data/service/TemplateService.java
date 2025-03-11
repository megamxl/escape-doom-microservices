package at.escapedoom.data.service;

import at.escapedoom.data.data.LevelRepository;
import at.escapedoom.data.data.SceneRepository;
import at.escapedoom.data.data.TemplateRepository;
import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.Scene;
import at.escapedoom.data.data.entity.Template;
import at.escapedoom.data.mapper.TemplateMapper;
import at.escapedoom.data.rest.model.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static at.escapedoom.data.utils.KeyCloakUtils.getUserId;

@Slf4j
@Service
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final LevelRepository levelRepository;
    private final SceneRepository sceneRepository;
    private final TemplateMapper templateMapper;

    public TemplateService(TemplateRepository repository, LevelRepository levelRepository,
            SceneRepository sceneRepository, TemplateMapper templateMapper) {
        this.templateRepository = repository;
        this.levelRepository = levelRepository;
        this.sceneRepository = sceneRepository;
        this.templateMapper = templateMapper;
    }

    public TemplateDTO createTemplate(@NonNull TemplateCreateRequestDTO request) {
        assert request != null;
        log.debug("Creating template with name: {}", request.getName());

        Template template = Template.builder().name(request.getName()).userId(getUserId()).level(Collections.EMPTY_LIST)
                .description(request.getDescription()).build();

        templateRepository.save(template);

        return templateMapper.toDTO(template);
    }

    @Transactional
    public TemplateResultDTO deleteTemplate(UUID templateId) {
        Optional<Template> templateOpt = templateRepository.findById(templateId);

        if (templateOpt.isEmpty()) {
            return TemplateResultDTO.builder().message("Template not found").build();
        }

        Template template = templateOpt.get();

        List<Level> levels = template.getLevel();
        if (levels != null && !levels.isEmpty()) {
            for (Level level : levels) {
                List<Scene> scenes = level.getScenes();
                if (scenes != null && !scenes.isEmpty()) {
                    sceneRepository.deleteAllInBatch(scenes);
                }
            }
            levelRepository.deleteAllInBatch(levels);
        }

        templateRepository.delete(template);

        return TemplateResultDTO.builder().message("Template deleted successfully, id: " + templateId).build();
    }

    public List<TemplateDTO> getAllTemplates() {
        log.debug("Fetching all templates");

        return templateRepository.findAllByUserId(getUserId()).stream().map(templateMapper::toDTO).toList();
    }

    public TemplateDTO getTemplateById(String templateId) {
        UUID id = validateUUID(templateId);

        Optional<Template> entityOpt = templateRepository.findById(id);
        assert entityOpt.isPresent();
        return templateMapper.toDTO(entityOpt.get());
    }

    public TemplateUpdateResultDTO updateTemplate(String templateId, TemplateUpdateRequestDTO request) {
        log.debug("Updating template with ID: {}", templateId);

        UUID id = validateUUID(templateId);

        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Template with ID " + templateId + " not found"));

        if (request.getName() != null) {
            template.setName(request.getName());
        }

        if (request.getDescription() != null) {
            template.setDescription(request.getDescription());
        }

        templateRepository.save(template);

        return TemplateUpdateResultDTO.builder().message("Template updated successfully").build();
    }

    private UUID validateUUID(String templateId) {
        try {
            return UUID.fromString(templateId);
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format: {}", templateId);
            throw new IllegalArgumentException("Invalid UUID format");
        }
    }
}
