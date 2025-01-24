package clinica.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "medico", uniqueConstraints = {
		@UniqueConstraint(columnNames = "dni"),
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "telefono"),
		@UniqueConstraint(columnNames = "direccion")
})
public class Medico extends Persona{
	private static final long serialVersionUID = 2L;
	
	@Column(nullable = false)
	private String especialidad;
	@Column(nullable = false)
	private double sueldo;
	
	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
	private List<Turno> turnos;
}
