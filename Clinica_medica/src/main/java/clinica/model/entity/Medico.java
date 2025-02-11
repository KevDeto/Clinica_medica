package clinica.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "medicos", uniqueConstraints = {
		@UniqueConstraint(columnNames = "dni"),
		@UniqueConstraint(columnNames = "email"),
})
public class Medico extends Persona{
	private static final long serialVersionUID = 2L;
	
	@Column(name = "especialidad", nullable = false)
	private String especialidad;
	@Column(name = "sueldo", nullable = false)
	private double sueldo;
	
	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
	private List<Turno> turnos;
	
	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
	private List<CitaMedica> citasMedicas;
}
