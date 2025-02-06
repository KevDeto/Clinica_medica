package clinica.security.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
