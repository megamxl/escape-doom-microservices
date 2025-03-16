package at.escapedoom.data.utils;

import at.escapedoom.spring.security.KeycloakUserUtil;

import java.util.NoSuchElementException;
import java.util.UUID;

public class KeyCloakUtils {

    public static UUID getUserId() {
        return KeycloakUserUtil.getCurrentUserUUID().orElseThrow(NoSuchElementException::new);
    }

}
