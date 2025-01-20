package clinica.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Medico extends Persona{
	private String especialidad;
//	private List<CitaMedica> cita_medica;
	private double sueldo;
}
