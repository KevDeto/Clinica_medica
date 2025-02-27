package clinica.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServicioMedicoDTO {
	private Long codigoServicio;
	private String nombre;
	private String descripcion;
	private double precio;

}
