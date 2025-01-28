package clinica.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import clinica.model.entity.DiaDeSemana;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurnoDTO implements Serializable{
//	@JsonIgnore
    private Long id;
    private DiaDeSemana dia;
    @Schema(example = "08:00:00")
    private LocalTime hora_inicio;
    @Schema(example = "12:00:00")
    private LocalTime hora_fin;
    @Schema(example = "0")
    private Long medico_id;
}

