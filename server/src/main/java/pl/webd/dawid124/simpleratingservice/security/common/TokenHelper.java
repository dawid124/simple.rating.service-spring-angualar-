package pl.webd.dawid124.simpleratingservice.security.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.webd.dawid124.simpleratingservice.security.model.SecurityUser;
import pl.webd.dawid124.simpleratingservice.utils.TimeUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Component
public class TokenHelper {

    public static final String BEARER = "Bearer ";

    @Value("${app.name}")
    private String APP_NAME;

    @Value("${jwt.secret}")
    public String SECRET;

    @Value("${jwt.header}")
    private String AUTH_HEADER;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String generateToken(String username) {
        return Jwts.builder()
                .setIssuer( APP_NAME )
                .setSubject(username)
                .setIssuedAt(TimeUtility.now())
                .signWith( SIGNATURE_ALGORITHM, SECRET )
                .compact();
    }

    public String getUsernameFromToken(String token) {
        String username;

        if (token == null || token.isEmpty()) {
            return null;
        }

        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public Boolean validateToken(String token, SecurityUser securityUser) {
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);
        return (
                username != null &&
                username.equals(securityUser.getUsername()) &&
                        !isCreatedBeforeLastPasswordReset(created, securityUser.getLastPasswordResetDate())
        );
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public String getTokenFromHeader(HttpServletRequest request ) {
        String authHeader = getAuthHeaderFromHeader( request );
        if ( authHeader != null && authHeader.startsWith(BEARER)) {
            return authHeader.substring(7);
        }

        return null;
    }

    public String getTokenFromParam(HttpServletRequest request ) {
        return request.getParameter(AUTH_HEADER);
    }

    public String getAuthHeaderFromHeader( HttpServletRequest request ) {
        return request.getHeader(AUTH_HEADER);
    }

}