package at.escapedoom.data.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@UtilityClass
public class UuidUtils {

    public UUID validateUUID(String templateId) {
        try {
            return UUID.fromString(templateId);
        } catch (IllegalArgumentException e) {
            log.error("Invalid UUID format: {}", templateId);
            throw new IllegalArgumentException("Invalid UUID format");
        }
    }
}
