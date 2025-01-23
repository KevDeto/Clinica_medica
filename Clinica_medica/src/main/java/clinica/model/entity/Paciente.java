package clinica.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "paciente", uniqueConstraints = {
		@UniqueConstraint(columnNames = "dni"),
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "telefono"),
		@UniqueConstraint(columnNames = "direccion")
})
public class Paciente extends Persona{
	private static final long serialVersionUID = 3L;

}
