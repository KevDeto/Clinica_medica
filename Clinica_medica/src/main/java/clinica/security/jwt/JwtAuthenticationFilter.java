package clinica.security.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import clinica.security.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter  extends UsernamePasswordAuthenticationFilter{

	private JwtUtils jwtUtils;

	public JwtAuthenticationFilter(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		UserEntity userEntity = null;
		String username = "";
		String password = "";
		try {
			//toma los parametros username y password para mapearlo a UserEntity
			userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
			username = userEntity.getUsername();
			password = userEntity.getPassword();
		} catch (StreamReadException e) {
			throw new RuntimeException(e);
		}catch(DatabindException e) {
			throw new RuntimeException(e);
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
		//con esto nos vamos a authenticar en la application
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(username, password);
			//se encarga de administrar la authentication
		return getAuthenticationManager().authenticate(authenticationToken);
	}

	//para generar el token nosotros debemos obtener los detalles del usuario, la contraseña etc...
	//para eso debemos crear una nueva clase UserDetails o manejar la de Spring.
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		//obtenemos los datos del usuario (esta clase es de spring framework, por eso se nombro UserEntity)
		User user = (User)authResult.getPrincipal();
		//generamos el token de acceso para dar autorizacion a los endpoints
//		String token = jwtUtils.generarToken(user.getUsername());
		
        String token;

        // Verificar si el usuario seleccionó "Recordar durante 30 días"
        boolean recordar = Boolean.parseBoolean(request.getParameter("recordar"));
        if (recordar) {
            token = jwtUtils.generarToken(user.getUsername(), 30 * 24 * 60 * 60 * 1000L); // 30 días
        } else {
            token = jwtUtils.generarToken(user.getUsername());
        }
        
		//respondemos con el token de acceso
		response.addHeader("Authorization", token);
		//respondemos en el cuerpo de la respuesto
		Map<String, Object> httpResponse = new HashMap<String, Object>();
		
		httpResponse.put("token", token);
		httpResponse.put("Message", "Autenticacion correcta");
		httpResponse.put("Username", user.getUsername());
		
		//A este mapa lo debemos convertir a un json con la libreria jackson
		response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
		response.setStatus(HttpStatus.OK.value());
		//cual va a ser el contenido de esa respuesta ?
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		//Nos aseguramos de que todo se escriba correctamente
		response.getWriter().flush();
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
}
