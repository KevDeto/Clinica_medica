package clinica.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtils {
//	@Value("${jwt.secret.key}")
//	private String secretKey;
//	@Value("${jwt.time.expiration}")
//	private String timeExpiration;
    private final SecretKey secretKey; 
    private final long timeExpiration; //expiración en milisegundos (30 días por defecto)

    public JwtUtils() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        this.timeExpiration = 30L * 24 * 60 * 60 * 1000;
    }
    
    // renueva el token si está por expirar
    public String renovarTokenSiProximoAExpirar(String token) {
        Claims claims = extraerTodosLosParametrosClaims(token);
        Date expiration = claims.getExpiration();
        Date now = new Date();

        // Renovar el token si expira en menos de 7 días
        if (expiration.before(new Date(now.getTime() + 7 * 24 * 60 * 60 * 1000))) {
            return generarToken(claims.getSubject(), timeExpiration); // Nuevo token con 30 días de expiración
        }
        return token;
    }
	
	//metodo que se encargara de crear un token
	public String generarToken(String username, Long expirationTime) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
//				.signWith(obtenerFirma(), SignatureAlgorithm.HS256)
				.signWith(secretKey, SignatureAlgorithm.HS256)
				.compact();
				
	}
	
    // Método original para generar un token con el tiempo de expiración por defecto
    public String generarToken(String username) {
        return generarToken(username, timeExpiration);
    }
	
	//validar token de acceso
	public boolean esTokenValido(String token) {
		try {
			Jwts.parserBuilder()
//			.setSigningKey(obtenerFirma())
			.setSigningKey(secretKey)
			.build()
			.parseClaimsJws(token)
			.getBody();
			
			return true;
		} catch (Exception e) {
			System.out.println("Token invalido, error: ".concat(e.getMessage()));
//			System.err.append("Token invalido, error: ".concat(e.getMessage()));
			return false;
		}
	}
	
	//obtener todos los claims del token
	public Claims extraerTodosLosParametrosClaims(String token){
		return 	Jwts.parserBuilder()
//				.setSigningKey(obtenerFirma())
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	//obtener un solo claim
	public <T> T obtenerClaim(String token, Function<Claims, T> claimsTFunction) {
		Claims claims = extraerTodosLosParametrosClaims(token);
		return claimsTFunction.apply(claims);
	}
	
	//obtener el username del toke
	public String obtenerUsernameDeToken(String token) {
		return obtenerClaim(token, Claims::getSubject);
	}
	
//	//obtener firma del token
//	public Key obtenerFirma() {
//		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//		return Keys.hmacShaKeyFor(keyBytes);
//	}
}
