package clinica.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import clinica.model.entity.DiaDeSemana;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

