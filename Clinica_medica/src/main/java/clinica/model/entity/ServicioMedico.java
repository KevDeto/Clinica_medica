package clinica.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "servicios")
public class ServicioMedico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long codigoServicio;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "precio")
	private double precio;
	
	@ManyToMany(mappedBy = "listaServicios", cascade = CascadeType.ALL)
	private List<PaqueteServicio> listaPaquetes;

	@OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
	private List<CitaMedica> citaMedica;
}
