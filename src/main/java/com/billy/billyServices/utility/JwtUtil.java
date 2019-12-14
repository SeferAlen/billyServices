package com.billy.billyServices.utility;

import com.billy.billyServices.enums.TokenStatus;
import com.billy.billyServices.model.BillyUser;
import com.billy.billyServices.model.Login;
import com.billy.billyServices.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

/**
 * Utility methods for json web token related actions
 */
@Component
public class JwtUtil implements Serializable {
    private static final String TOKEN_NULL = "Token must not be null";
    private static final String CLAIMS_RESOLVER_NULL = "Claims resolver function must not be null";
    private static final String LOGIN_NULL = "Login must not be null";
    private static final String PRO_TIP_USER_NULL = "ProTipUser must not be null";
    private static final String CLAIM_ROLE = "Role";
    private static final String CLAIM_VALIDITY_DATE = "ProTipUserValidityDate";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final int TOKEN_EXPIRATION = 5000;
    private static final long serialVersionUID = -2550185165626007488L;
    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private static String jwtSecret;

    @Value("${jwt.secret}")
    public void setJwtSecret(final String secret) {
        jwtSecret = secret;
    }

    /**
     * Method for generating token from login data
     *
     * @param login {@link Login} the login
     * @return {@link String} the token
     */
    public static String generateToken(final Login login) {
        Objects.requireNonNull(login, LOGIN_NULL);

        final Map<String, Object> claims = new HashMap<>();
        final Set<Role> loginRolesSet = login.getRoles();

        final List<String> loginRolesList = new ArrayList<String>();
        for (final Role role : loginRolesSet) {
            loginRolesList.add(role.getName());
        }

        final String roles = String.join(", ", loginRolesList);
        claims.put(CLAIM_ROLE, roles);

        return doGenerateToken(claims, login.getUsername());
    }

    /**
     * Method for token generation
     *
     * @param claims {@link Map<String, Object>} the claims
     * @param subject {@link String}             the token
     * @return {@link String}                    the token with claims
     */
    private static String doGenerateToken(final Map<String, Object> claims, final String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    /**
     * Method for getting username from token claims
     *
     * @param token {@link String} the token
     * @return {@link String}      the username
     */
    public static String getUsernameFromToken(final String token) {
        Objects.requireNonNull(token, TOKEN_NULL);

        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Method for getting expiration date from token claims
     *
     * @param token {@link String} the token
     * @return {@link Date}        the expiration date
     */
    public static Date getExpirationDateFromToken(final String token) {
        Objects.requireNonNull(token, TOKEN_NULL);

        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Generic method for getting claim from token
     *
     * @param token          {@link String}              the token
     * @param claimsResolver {@link Function<Claims, T>} the function
     * @return {@link T}                                 the claim
     */
    public static <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
        Objects.requireNonNull(token, TOKEN_NULL);
        Objects.requireNonNull(claimsResolver, CLAIMS_RESOLVER_NULL);

        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Method for getting all claims from token
     *
     * @param token {@link String} the token
     * @return {@link Claims}      the claims
     */
    public static Claims getAllClaimsFromToken(final String token) {
        Objects.requireNonNull(token, TOKEN_NULL);

        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    /**
     * Method for getting role from token claims
     *
     * @param token {@link String} the token
     * @return {@link Claims}      the claims
     */
    public static String getRoleFromToken(final String token) {
        Objects.requireNonNull(token, TOKEN_NULL);

        final Claims claims = getAllClaimsFromToken(token);
        return claims.get(CLAIM_ROLE).toString();
    }


    /**
     * Method for decoding token
     *
     * @param token {@link String} the token
     * @return {@link Claims}      the claims
     */
    public static Claims decodeJWT(final String token) {
        Objects.requireNonNull(token, TOKEN_NULL);

        final Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                .parseClaimsJws(token).getBody();

        return claims;
    }

    /**
     * Method for validating token
     *
     * @param token {@link String} the token
     */
    public static TokenStatus validateToken(final String token) {
        Objects.requireNonNull(token, TOKEN_NULL);

        if(isTokenExpired(token)) return TokenStatus.EXPIRED;
        decodeJWT(token);

        return TokenStatus.OK;
    }


    /**
     * Method for checking is token expired
     *
     * @param token {@link String} the token
     * @return {@link boolean}     the token expire status
     */
    private static Boolean isTokenExpired(final String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

}
