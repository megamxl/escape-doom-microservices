package at.escapedoom.data.service;

import at.escapedoom.data.rest.api.LevelApiDelegate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@Service
public class LevelApiServiceImpl implements LevelApiDelegate {

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return LevelApiDelegate.super.getRequest();
    }

}
