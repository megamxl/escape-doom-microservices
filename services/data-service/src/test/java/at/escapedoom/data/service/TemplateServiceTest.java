 package at.escapedoom.data.service;

 import at.escapedoom.data.DataApi;
 import at.escapedoom.data.data.LevelRepository;
 import at.escapedoom.data.data.SceneRepository;
 import at.escapedoom.data.data.TemplateRepository;
 import at.escapedoom.data.data.entity.Template;
 import at.escapedoom.data.rest.model.*;
 import at.escapedoom.data.utils.KeyCloakUtils;
 import jakarta.persistence.EntityNotFoundException;
 import jakarta.transaction.Transactional;
 import org.junit.jupiter.api.AfterAll;
 import org.junit.jupiter.api.BeforeAll;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.mockito.MockedStatic;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.test.context.ActiveProfiles;

 import java.util.List;
 import java.util.UUID;

 import static org.assertj.core.api.Assertions.assertThat;
 import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
 import static org.mockito.Mockito.mockStatic;

 @SpringBootTest(classes = DataApi.class)
 @ActiveProfiles("test")
 class TemplateServiceTest {

 private String VALID_TEMPLATE_ID = "";

 private static final String INVALID_TEMPLATE_ID = "05c48cb1-a3aa-4673-8d24-666666666666";
 private static final UUID MOCK_USER_ID = UUID.fromString("123e4567-e89b-12d3-a456-556642440000");

 @Autowired
 private TemplateService service;

 @Autowired
 private TemplateRepository templateRepository;

 @Autowired
 private LevelRepository levelRepository;

 @Autowired
 private SceneRepository sceneRepository;

 private static MockedStatic<KeyCloakUtils> mockedKeycloak;

 @BeforeAll
 static void init() {
 mockedKeycloak = mockStatic(KeyCloakUtils.class);
 mockedKeycloak.when(KeyCloakUtils::getUserId).thenReturn(MOCK_USER_ID);
 }

 @BeforeEach
 void setup() {
 Template template = Template.builder().name("Test Template").description("A test template").userId(MOCK_USER_ID)
 .build();

 VALID_TEMPLATE_ID = templateRepository.save(template).getTemplateId().toString();
 }

 // region GET Tests
 @Test
 void testGetAllTemplates() {
 List<TemplateDTO> templates = service.getAllTemplates();
 assertThat(templates).hasSize(1);
 assertThat(templates.get(0).getName()).isEqualTo("Test Template");
 }

 @Test
 void testGetTemplateById() {
 TemplateDTO template = service.getTemplateById(VALID_TEMPLATE_ID);
 assertThat(template.getName()).isEqualTo("Test Template");
 }

 @Test
 void testGetTemplateByIdError() {
 assertThatThrownBy(() -> service.getTemplateById(INVALID_TEMPLATE_ID)).isInstanceOf(AssertionError.class);
 }
 // endregion

 // region DELETE Tests
 @Test
 @Transactional
 void testDeleteTemplate() {
 TemplateResultDTO response = service.deleteTemplate(UUID.fromString(VALID_TEMPLATE_ID));
 assertThat(response.getMessage()).isEqualTo("Template deleted successfully, id: " + VALID_TEMPLATE_ID);
 }

 @Test
 @Transactional
 void testDeleteTemplateNotFoundError() {
 TemplateResultDTO result = service.deleteTemplate(UUID.fromString(INVALID_TEMPLATE_ID));

 assertThat(result).isNotNull();
 assertThat(result.getMessage()).isEqualTo("Template not found");
 }
 // endregion

 // region PUT Tests
 @Test
 @Transactional
 void testUpdateTemplate() {
 TemplateDTO template = service.getTemplateById(VALID_TEMPLATE_ID);
 final String newName = "Updated Template";

 TemplateUpdateRequestDTO updateRequest = TemplateUpdateRequestDTO.builder().name(newName)
 .description("Updated Description").build();

 TemplateUpdateResultDTO updateResult = service.updateTemplate(template.getTemplateId(), updateRequest);

 assertThat(updateResult).isNotNull();
 assertThat(updateResult.getMessage()).isEqualTo("Template updated successfully");

 TemplateDTO updatedTemplate = service.getTemplateById(template.getTemplateId());
 assertThat(updatedTemplate.getName()).isEqualTo(newName);
 }

 @Test
 @Transactional
 void testUpdateTemplateNotFoundError() {
 TemplateUpdateRequestDTO updateRequest = TemplateUpdateRequestDTO.builder().name("New Name").build();

 assertThatThrownBy(() -> service.updateTemplate(INVALID_TEMPLATE_ID, updateRequest))
 .isInstanceOf(EntityNotFoundException.class)
 .hasMessageContaining("Template with ID " + INVALID_TEMPLATE_ID + " not found");
 }
 // endregion

 // region POST Tests
 @Test
 @Transactional
 void testCreateTemplate() {
 TemplateCreateRequestDTO createRequest = TemplateCreateRequestDTO.builder().name("New Template")
 .description("This is a new template").build();

 TemplateDTO template = service.createTemplate(createRequest);

 assertThat(template).isNotNull();
 assertThat(template.getName()).isEqualTo("New Template");
 }

 @Test
 @Transactional
 void testCreateTemplateNullError() {
 assertThatThrownBy(() -> service.createTemplate(null)).isInstanceOf(AssertionError.class);
 }
 // endregion

 // Ensure mock is closed after all tests
 @AfterAll
 static void closeMock() {
 mockedKeycloak.close();
 }
 }
