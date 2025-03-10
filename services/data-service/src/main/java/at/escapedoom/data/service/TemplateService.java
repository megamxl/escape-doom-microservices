package at.escapedoom.data.service;

import at.escapedoom.data.data.TemplateRepository;
import at.escapedoom.data.data.entity.Template;
import at.escapedoom.data.mapper.TemplateMapper;
import at.escapedoom.data.rest.model.*;
import jakarta.persistence.EntityNotFoundException;
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

    private final TemplateRepository repository;
    private final TemplateMapper templateMapper;

    public TemplateService(TemplateRepository repository, TemplateMapper templateMapper) {
        this.repository = repository;
        this.templateMapper = templateMapper;
    }

    public TemplateDTO createTemplate(@NonNull TemplateCreateRequestDTO request) {
        assert request != null;
        log.debug("Creating template with name: {}", request.getName());

        Template template = Template.builder().name(request.getName()).userId(getUserId()).level(Collections.EMPTY_LIST)
                .description(request.getDescription()).build();

        repository.save(template);

        return templateMapper.toDTO(template);
    }

    public TemplateResultDTO deleteTemplate(String templateId) {
        log.debug("Deleting template with ID: {}", templateId);

        UUID id = validateUUID(templateId);

        if (!repository.existsById(id)) {
            return TemplateResultDTO.builder().message("Template not found").build();
        }

        repository.deleteById(id);
        log.debug("Template with ID {} successfully deleted", templateId);

        return TemplateResultDTO.builder().message("Template deleted successfully").build();
    }

    public List<TemplateDTO> getAllTemplates() {
        log.debug("Fetching all templates");

        return repository.findAllByUserId(getUserId()).stream().map(templateMapper::toDTO).toList();
    }

    public TemplateDTO getTemplateById(String templateId) {
        UUID id = validateUUID(templateId);

        Optional<Template> entityOpt = repository.findById(id);
        assert entityOpt.isPresent();
        return templateMapper.toDTO(entityOpt.get());
    }

    public TemplateUpdateResultDTO updateTemplate(String templateId, TemplateUpdateRequestDTO request) {
        log.debug("Updating template with ID: {}", templateId);

        UUID id = validateUUID(templateId);

        Template template = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Template with ID " + templateId + " not found"));

        if (request.getName() != null) {
            template.setName(request.getName());
        }

        if (request.getDescription() != null) {
            template.setDescription(request.getDescription());
        }

        repository.save(template);

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
