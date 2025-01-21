package clinica.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class MedicoDTO {
	@JsonIgnore
	private Long id;
	private String nombre;
	private String apellido;
	private String dni;
	private LocalDate fecha_nac;
	private String email;
	private String telefono;
	private String direccion;
	private String especialidad;
	private double sueldo;

//	private List<Long> cita_medica;
}
