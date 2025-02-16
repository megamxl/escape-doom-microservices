package at.escapedoom.spring.components.okhttp;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.io.IOException;

public class AuthorizationHeaderIntercept implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof JwtAuthenticationToken jwt) {
            return chain.proceed( request.newBuilder().header("Authorization", "Bearer " +  jwt.getToken().getTokenValue()).build());
        }
        return chain.proceed(request);
    }
}
