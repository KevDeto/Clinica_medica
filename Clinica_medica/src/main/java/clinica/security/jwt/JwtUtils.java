package clinica.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtils {
	@Value("${jwt.secret.key}")
	private String secretKey;
	@Value("${jwt.time.expiration}")
	private String timeExpiration;
	
	//metodo que se encargara de crear un token
	public String generarToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
				.signWith(obtenerFirma(), SignatureAlgorithm.HS256)
				.compact();
				
	}
	
	//validar token de acceso
	public boolean esTokenValido(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(obtenerFirma())
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
				.setSigningKey(obtenerFirma())
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
	
	//obtener firma del token
	public Key obtenerFirma() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
