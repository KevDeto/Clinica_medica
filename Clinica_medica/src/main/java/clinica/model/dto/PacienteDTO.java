package clinica.model.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PacienteDTO {
	private Long id_paciente;
	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fehca_nac;
	private String email;
	private String telefono;
	private String direccion;
}
