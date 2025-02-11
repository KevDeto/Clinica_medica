package clinica.model.dto;

import java.time.LocalTime;
import java.util.List;

import clinica.model.entity.DiaDeSemana;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TurnoDTO{
    private Long codigoTurno;
    private DiaDeSemana diaTurno;
    @Schema(example = "08:00:00")
    private LocalTime horaInicio;
    @Schema(example = "12:00:00")
    private LocalTime horaFin;
    private Long medicoId;
    private List<Long> citasMedicas;
}

