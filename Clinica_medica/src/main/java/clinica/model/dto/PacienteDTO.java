package clinica.model.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class PacienteDTO {
	private Long id;
	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fechaNac;
	private String email;
	private String telefono;
	private String direccion;
}
