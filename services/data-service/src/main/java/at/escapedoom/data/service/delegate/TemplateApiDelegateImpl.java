package at.escapedoom.data.service.delegate;

import at.escapedoom.data.rest.api.TemplateApiDelegate;
import at.escapedoom.data.rest.model.*;
import at.escapedoom.data.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TemplateApiDelegateImpl implements TemplateApiDelegate {

    private final TemplateService templateService;

    @Override
    public ResponseEntity<TemplateDTO> createTemplate(TemplateCreateRequestDTO templateId) {
        return new ResponseEntity<>(templateService.createTemplate(templateId), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TemplateResultDTO> deleteTemplate(String templateId) {
        return new ResponseEntity<>(templateService.deleteTemplate(UUID.fromString(templateId)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TemplateDTO>> getAllTemplates() {
        return new ResponseEntity<>(templateService.getAllTemplates(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TemplateDTO> getTemplate(String templateId) {
        TemplateDTO template = templateService.getTemplateById(templateId);
        return new ResponseEntity<>(template, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TemplateUpdateResultDTO> updateTemplate(String templateId, TemplateUpdateRequestDTO request) {
        return new ResponseEntity<>(templateService.updateTemplate(templateId, request), HttpStatus.OK);
    }
}
