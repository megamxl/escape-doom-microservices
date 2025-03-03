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
    public ResponseEntity<EscapeRoomTemplateDTO> createTemplate(
            EscapeRoomTemplateCreateRequestDTO escapeRoomTemplateDTO) {
        return new ResponseEntity<>(templateService.createTemplate(escapeRoomTemplateDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EscapeRoomTemplateResultDTO> deleteTemplate(String escapeRoomTemplateId) {
        return new ResponseEntity<>(templateService.deleteTemplate(escapeRoomTemplateId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EscapeRoomTemplateDTO>> getAllTemplates() {
        return new ResponseEntity<>(templateService.getAllTemplates(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EscapeRoomTemplateDTO> getTemplate(String escapeRoomTemplateId) {
        EscapeRoomTemplateDTO template = templateService.getTemplate(escapeRoomTemplateId);
        return new ResponseEntity<>(template, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EscapeRoomTemplateUpdateResultDTO> putTemplate(String escapeRoomTemplateId,
            EscapeRoomTemplateUpdateRequestDTO request) {
        return new ResponseEntity<>(templateService.updateTemplate(escapeRoomTemplateId, request), HttpStatus.OK);
    }
}
