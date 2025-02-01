package clinica.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "paquetes")
public class PaqueteServicio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoPaquete;
	private double precio;
	@ManyToMany
	@JoinTable(
			name = "paquete_servicio",
			joinColumns = @JoinColumn(name = "paquete_id"),
			inverseJoinColumns = @JoinColumn(name = "servicio_id"))
	private List<ServicioMedico> listaServicios;
}
