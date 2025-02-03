package clinica.model.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaqueteServicioDTO {
	private Long codigoPaquete;
	private double precio;
	private List<Long> listaServicios;
}
