package clinica.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name = "servicios")
public class ServicioMedico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoServicio;
	private String nombre;
	private String descripcion;
	private double precio;
	
	@ManyToMany(mappedBy = "listaServicios", cascade = CascadeType.ALL)
	private List<PaqueteServicio> listaPaquetes;

	@OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
	private List<CitaMedica> citaMedica;
}
