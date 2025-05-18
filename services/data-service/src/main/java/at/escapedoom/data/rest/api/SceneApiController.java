package at.escapedoom.data.rest.api;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
@Controller
@RequestMapping("${openapi.escapeDoomData.base-path:/v1}")
public class SceneApiController implements SceneApi {

    private final SceneApiDelegate delegate;

    public SceneApiController(@Autowired(required = false) SceneApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new SceneApiDelegate() {
        });
    }

    @Override
    public SceneApiDelegate getDelegate() {
        return delegate;
    }

}
