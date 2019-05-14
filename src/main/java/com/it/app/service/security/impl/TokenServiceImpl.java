package com.it.app.service.security.impl;

import com.it.app.security.model.AuthenticationUserDetails;
import com.it.app.service.security.TokenService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Implementation Token service interface
 */
@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    //TODO delete
    //private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    private static final String INVALID_JWT_SIGNATURE_MESSAGE = "Invalid JWT signature";

    private static final String INVALID_JWT_TOKEN_MESSAGE = "Invalid JWT token";

    private static final String EXPIRED_JWT_TOKEN_MESSAGE = "Expired JWT token";

    private static final String UNSUPPORTED_JWT_TOKEN_MESSAGE = "Unsupported JWT token";

    private static final String JWT_CLAIMS_STRING_IS_EMPTY_MESSAGE = "JWT claims string is empty";

    private static final String JWT_SECRET = "secret";

    private static final Integer JWT_EXPIRATION_MILLIS = 6000000;

    /**
     * Generate JWT
     *
     * @param authentication - Authentication object
     * @return - String JWT
     */
    @Override
    public String generate(Authentication authentication) {
        return generate(((AuthenticationUserDetails) authentication.getPrincipal()).getUsername());
    }

    /**
     * Refresh existing JWT
     *
     * @param token - existing JWT
     * @return - new JWT
     */
    @Override
    public String refresh(String token) {
        return generate(extractUsername(token));
    }

    /**
     * Extract username from JWT
     *
     * @param token - JWT
     * @return - String JWT subject (unique identifies the subject of the JWT)
     */
    @Override
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    /**
     * Validate Token by JWT_SECRET
     *
     * @param authToken - JWT for validation
     * @return - true, if JWT validate successful
     */
    @Override
    public boolean validate(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error(INVALID_JWT_SIGNATURE_MESSAGE, e);
            throw e;
        } catch (MalformedJwtException e) {
            log.error(INVALID_JWT_TOKEN_MESSAGE, e);
            throw e;
        } catch (ExpiredJwtException e) {
            log.error(EXPIRED_JWT_TOKEN_MESSAGE, e);
            throw e;
        } catch (UnsupportedJwtException e) {
            log.error(UNSUPPORTED_JWT_TOKEN_MESSAGE, e);
            throw e;
        } catch (IllegalArgumentException e) {
            log.error(JWT_CLAIMS_STRING_IS_EMPTY_MESSAGE, e);
            throw e;
        }
    }

    private String generate(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MILLIS))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }
}
