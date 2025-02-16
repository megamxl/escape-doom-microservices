package at.escapedoom.data.services;

import at.escapedoom.data.rest.api.TemplateApiDelegate;
import at.escapedoom.data.rest.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@Component
public class TemplateApiService implements TemplateApiDelegate {

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return TemplateApiDelegate.super.getRequest();
    }

    @Override
    public ResponseEntity<EscapeRoomTemplateResult> createTemplate(
            EscapeRoomTemplateCreateRequest escapeRoomTemplateCreateRequest) {
        var escape = new EscapeRoomTemplateResult();
        escape.setMessage("ILikeCheese");
        return new ResponseEntity<>(escape, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EscapeRoomTemplateResult> deleteTemplate(String escapeRoomTemplateId) {
        return TemplateApiDelegate.super.deleteTemplate(escapeRoomTemplateId);
    }

    @Override
    public ResponseEntity<List<EscapeRoomTemplateDTO>> getAllTemplates() {
        return TemplateApiDelegate.super.getAllTemplates();
    }

    @Override
    public ResponseEntity<EscapeRoomTemplate> getTemplate(String escapeRoomTemplateId) {
        return TemplateApiDelegate.super.getTemplate(escapeRoomTemplateId);
    }

    @Override
    public ResponseEntity<EscapeRoomTemplateUpdateResult> putTemplate(String escapeRoomTemplateId,
            EscapeRoomTemplateUpdateRequest escapeRoomTemplateUpdateRequest) {
        return TemplateApiDelegate.super.putTemplate(escapeRoomTemplateId, escapeRoomTemplateUpdateRequest);
    }
}
