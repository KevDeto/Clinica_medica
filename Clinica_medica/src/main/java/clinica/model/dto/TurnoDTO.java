package clinica.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import clinica.model.entity.EstadoTurno;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurnoDTO {
	@JsonIgnore
	private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
    @JsonFormat(pattern = "HH:mm:ss")
	private LocalTime hora_inicio;
    @JsonFormat(pattern = "HH:mm:ss")
	private LocalTime hora_fin;
	@JsonIgnore
	private Long medico_id;
	private EstadoTurno estado;
}
