package at.escapedoom.spring.security;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

public class KeycloakUserUtil {
    public static Optional<UUID> getCurrentUserUUID() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) authentication;
            UUID userId = UUID.fromString(jwtToken.getToken().getClaimAsString("sub"));
            return Optional.of(userId);
        }

        return Optional.empty();
    }
}
