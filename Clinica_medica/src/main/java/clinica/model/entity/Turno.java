package clinica.model.entity;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turnos")
public class Turno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long codigoTurno;
	@Enumerated(EnumType.STRING)
	@Column(name = "dia", nullable = false)
	private DiaDeSemana diaTurno;
	@Column(name = "hora_inicio", nullable = false)
	private LocalTime horaInicio;
	@Column(name = "hora_fin", nullable = false)
	private LocalTime horaFin;
	
	@ManyToOne
	@JoinColumn(name = "medico_id", nullable = false)
	private Medico medico;
	
	@OneToMany(mappedBy = "turno", cascade = CascadeType.ALL)
	private List<CitaMedica> citasMedicas;
}
