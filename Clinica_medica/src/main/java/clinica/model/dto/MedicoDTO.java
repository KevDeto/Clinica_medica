package clinica.model.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicoDTO {
	private Long codigoMedico;
	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fechaNac;
	private String email;
	private String telefono;
	private String direccion;
	private String especialidad;
	private double sueldo;
	private List<Long> turnos;
}
