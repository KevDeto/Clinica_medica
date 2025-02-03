package clinica.model.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServicioMedicoDTO {
	private Long codigoServicio;
	private String nombre;
	private String descripcion;
	private double precio;

}
