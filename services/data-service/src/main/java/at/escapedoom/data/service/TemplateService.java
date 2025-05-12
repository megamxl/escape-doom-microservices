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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static at.escapedoom.data.utils.KeyCloakUtils.getUserId;

@Slf4j
@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final LevelRepository levelRepository;
    private final SceneRepository sceneRepository;
    private final TemplateMapper templateMapper;

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
        Template template = templateRepository.findById(templateId)
                .orElseThrow(() -> new NoSuchElementException("Template not found: " + templateId));

        templateRepository.delete(template);
        templateRepository.flush();

        return TemplateResultDTO.builder().message("Template deleted successfully, id: " + templateId).build();
    }

    public List<TemplateDTO> getAllTemplates() {
        log.debug("Fetching all templates");

        return templateRepository.findAllByUserId(getUserId()).stream().map(templateMapper::toDTO).toList();
    }

    public TemplateDTO getTemplateById(String templateId) {
        Template template = templateRepository.findById(UUID.fromString(templateId))
                .orElseThrow(() -> new NoSuchElementException("Template not found: " + templateId));
        return templateMapper.toDTO(template);
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
