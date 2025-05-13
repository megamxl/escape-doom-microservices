package at.escapedoom.data.service.rest.factory;

import at.escapedoom.data.rest.model.TemplateCreateRequestDTO;
import at.escapedoom.data.rest.model.TemplateDTO;

public class TemplateTestFactory {

    public static TemplateCreateRequestDTO createRequest() {
        return TemplateCreateRequestDTO.builder().name("My Escape Template").description("Test Description").build();
    }

    public static TemplateDTO createResponseFrom(TemplateCreateRequestDTO request) {
        return TemplateDTO.builder().templateId("abc123").name(request.getName()).description(request.getDescription())
                .build();
    }
}
