package clinica.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turno")
public class Turno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private LocalDate fecha;
	@Column(nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
	private LocalTime hora_inicio;//hora inicial
	@Column(nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
	private LocalTime hora_fin;//le agrego 5 min a la hora inicial;
	
	@ManyToOne
	@JoinColumn(name = "medico_id", nullable = false)
	private Medico medico;
	
	@Enumerated(EnumType.STRING)
	private EstadoTurno estado = EstadoTurno.DISPONIBLE;
}
