package at.escapedoom.data.service.delegate;

import at.escapedoom.data.rest.api.TemplateApiDelegate;
import at.escapedoom.data.rest.model.*;
import at.escapedoom.data.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TemplateApiDelegateImpl implements TemplateApiDelegate {

    private final TemplateService templateService;

    @Override
    public ResponseEntity<TemplateDTO> createTemplate(TemplateCreateRequestDTO TemplateDTO) {
        return new ResponseEntity<>(templateService.createTemplate(TemplateDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TemplateResultDTO> deleteTemplate(String TemplateId) {
        return new ResponseEntity<>(templateService.deleteTemplate(TemplateId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TemplateDTO>> getAllTemplates() {
        return new ResponseEntity<>(templateService.getAllTemplates(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TemplateDTO> getTemplate(String TemplateId) {
        TemplateDTO template = templateService.getTemplate(TemplateId);
        return new ResponseEntity<>(template, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TemplateUpdateResultDTO> putTemplate(String TemplateId, TemplateUpdateRequestDTO request) {
        return new ResponseEntity<>(templateService.updateTemplate(TemplateId, request), HttpStatus.OK);
    }
}
