package clinica.model.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PacienteDTO {
	private Long codigoPaciente;
	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fechaNac;
	private String email;
	private String telefono;
	private String direccion;
}
