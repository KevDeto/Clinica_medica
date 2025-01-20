package clinica.model.entity;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Entity
public class Paciente extends Persona{

}
