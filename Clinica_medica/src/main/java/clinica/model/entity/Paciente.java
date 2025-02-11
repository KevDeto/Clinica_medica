package clinica.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "pacientes", uniqueConstraints = {
		@UniqueConstraint(columnNames = "dni"),
		@UniqueConstraint(columnNames = "email"),
})
public class Paciente extends Persona{
	private static final long serialVersionUID = 3L;
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
	private List<CitaMedica> citasMedicas;
}
