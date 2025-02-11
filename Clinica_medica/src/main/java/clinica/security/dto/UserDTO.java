package clinica.security.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	private Set<String> roles;
}
