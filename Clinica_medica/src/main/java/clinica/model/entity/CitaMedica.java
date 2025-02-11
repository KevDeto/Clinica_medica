package clinica.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "citas_medicas")
public class CitaMedica {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long codigoCitaMedica;
	@Column(name = "fecha_consulta")
	private LocalDate fechaConsulta;
	@Column(name = "hora_consulta")
	private LocalTime horaConsulta;
	@Column(name = "monto_total")
	private double montoTotal;
	@Column(name = "pagado")
	private String pagado;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id", nullable = false)
	private Paciente paciente;
	@ManyToOne
	@JoinColumn(name = "medico_id", nullable = false)
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name = "turno_id", nullable = false)
	private Turno turno;
	
	@ManyToOne
	@JoinColumn(name = "servicio_id", nullable = false)
	private ServicioMedico servicio;
	
	@ManyToOne
	@JoinColumn(name = "paquete_id", nullable = false)
	private PaqueteServicio paquete;
}
