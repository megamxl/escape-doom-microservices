package at.escapedoom.data.delegate;

import at.escapedoom.data.TemplateNotFoundException;
import at.escapedoom.data.rest.api.TemplateApiDelegate;
import at.escapedoom.data.rest.model.*;
import at.escapedoom.data.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TemplateApiDelegateImpl implements TemplateApiDelegate {

    private final TemplateService templateService;

    public TemplateApiDelegateImpl(TemplateService templateService) {
        this.templateService = templateService;
    }

    @Override
    public ResponseEntity<EscapeRoomTemplateResult> createTemplate(EscapeRoomTemplateCreateRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(templateService.createTemplate(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(EscapeRoomTemplateResult.builder().message("Invalid data").build());
        }
    }

    @Override
    public ResponseEntity<EscapeRoomTemplateResult> deleteTemplate(String escapeRoomTemplateId) {
        try {
            return ResponseEntity.ok(templateService.deleteTemplate(escapeRoomTemplateId));
        } catch (TemplateNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(EscapeRoomTemplateResult.builder().message(e.getMessage()).build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(EscapeRoomTemplateResult.builder().message("Invalid UUID format").build());
        }
    }

    @Override
    public ResponseEntity<List<EscapeRoomTemplateDTO>> getAllTemplates() {
        return ResponseEntity.ok(templateService.getAllTemplates());
    }

    @Override
    public ResponseEntity<at.escapedoom.data.rest.model.EscapeRoomTemplate> getTemplate(String escapeRoomTemplateId) {
        try {
            at.escapedoom.data.rest.model.EscapeRoomTemplate template = templateService
                    .getTemplate(escapeRoomTemplateId);
            return ResponseEntity.ok(template);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (TemplateNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Override
    public ResponseEntity<EscapeRoomTemplateUpdateResult> putTemplate(String escapeRoomTemplateId,
            EscapeRoomTemplateUpdateRequest request) {
        try {
            return ResponseEntity.ok(templateService.updateTemplate(escapeRoomTemplateId, request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(EscapeRoomTemplateUpdateResult.builder().message("Invalid UUID format").build());
        } catch (TemplateNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
