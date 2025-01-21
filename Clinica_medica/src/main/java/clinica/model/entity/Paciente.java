package clinica.model.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@NoArgsConstructor
@SuperBuilder
@Entity
public class Paciente extends Persona{

}
