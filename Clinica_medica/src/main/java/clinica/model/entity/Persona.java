package clinica.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	@Column(nullable = false)
	private String nombre;
//	@Column(nullable = false)
	private String apellido;
//	@Column(nullable = false, unique = true)
	private String dni;
//	@Column(nullable = false)
	private LocalDate fechaNac;
//	@Email
//	@Column(nullable = false, unique = true)
	private String email;
//	@Column(nullable = false, unique = true)
	private String telefono;
//	@Column(nullable = false, unique = true)
	private String direccion;
}
