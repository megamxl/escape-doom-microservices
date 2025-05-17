package at.escapedoom.data.rest.api;

import jakarta.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.11.0")
@Controller
@RequestMapping("${openapi.escapeDoomData.base-path:/v1}")
public class NodeApiController implements NodeApi {

    private final NodeApiDelegate delegate;

    public NodeApiController(@Autowired(required = false) NodeApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new NodeApiDelegate() {
        });
    }

    @Override
    public NodeApiDelegate getDelegate() {
        return delegate;
    }

}
