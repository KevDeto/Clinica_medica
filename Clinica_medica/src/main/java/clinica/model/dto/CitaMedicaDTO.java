package clinica.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import clinica.model.entity.Medico;
import clinica.model.entity.Paciente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CitaMedicaDTO {
	private Long id;
	private LocalDate fechaConsulta;
	@Schema(example = "08:00:00")
	private LocalTime horaConsulta;
	private double montoTotal;
	@Schema(example = "si/no")
	private String pagado;
	private Long pacienteId;
	private Long medicoId;
	private Long turnoId;
	private Long paqueteId;
	private Long servicioId;
}
