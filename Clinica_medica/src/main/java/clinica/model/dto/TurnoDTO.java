package clinica.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
    private Long id;
//    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate fecha;
//    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    @Schema(example = "08:00")
    private LocalTime hora_inicio;
//    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    @Schema(example = "12:00")
    private LocalTime hora_fin;
    @Schema(example = "0")
    private Long medico_id;
}

