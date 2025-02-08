package clinica.security.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import clinica.security.dao.IUserRepository;
import clinica.security.dto.UserDTO;
import clinica.security.entity.ERol;
import clinica.security.entity.RolEntity;
import clinica.security.entity.UserEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	//DEBO REDIRIGIR EL LOGIN A MI SWAGGER O DONDE QUIERA (UNA VEZ LOGUEADO9)
	@PostMapping("/crear")
	public ResponseEntity<?> crearUsuario(@Valid @RequestBody UserDTO userDTO){
		Set<RolEntity> roles = userDTO.getRoles().stream()
				.map(rol -> RolEntity.builder()
						.name(ERol.valueOf(rol))
						.build())
				.collect(Collectors.toSet());
		
		UserEntity userEntity = UserEntity.builder()
				.username(userDTO.getUsername())
				.password(passwordEncoder.encode(userDTO.getPassword()))
				.email(userDTO.getEmail())
				.roles(roles)
				.build();
		userRepository.save(userEntity);
		
		return ResponseEntity.ok(userEntity);
	}
	
	@DeleteMapping("/delete")
	public String deleteUser(@RequestParam String id) {
		userRepository.deleteById(Long.parseLong(id));
		return "se elimino correctamente al usuario: ".concat(id);
	}
}
