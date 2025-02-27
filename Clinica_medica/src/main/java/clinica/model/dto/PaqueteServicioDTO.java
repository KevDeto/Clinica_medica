package clinica.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaqueteServicioDTO {
	private Long codigoPaquete;
	private double precio;
	private List<Long> listaServicios;
}
