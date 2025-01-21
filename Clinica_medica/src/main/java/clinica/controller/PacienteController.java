package clinica.controller;

import java.util.List;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clinica.model.dto.PacienteDTO;
import clinica.model.payload.MensajeResponse;
import clinica.service.IPacienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class PacienteController {
	
	@Autowired
	private IPacienteService pacienteService;

	@PostMapping("/paciente")
	public ResponseEntity<MensajeResponse> crear(@Valid@RequestBody PacienteDTO pacienteDTO){
		try {
			PacienteDTO pacienteCreado = pacienteService.crearPaciente(pacienteDTO);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(MensajeResponse.builder()
							.mensaje("Paciente creado correctamente.")
							.objeto(pacienteCreado)
							.build());	
		} catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
					.body(MensajeResponse.builder()
							.mensaje("Error al crear paciente: " + e.getMessage())
							.objeto(null)
							.build());
		}
	}
	
	@GetMapping("/paciente/{id}")
	public ResponseEntity<MensajeResponse> obtener(@Valid@PathVariable Long id){
		PacienteDTO pacienteObtenido = pacienteService.obtenerPaciente(id);
		if(pacienteObtenido == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(MensajeResponse.builder()
							.mensaje("Paciente con ID " + id + " no encontrado.")
							.objeto(null)
							.build());
		}
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Paciente obtenido correctamente.")
				.objeto(pacienteObtenido)
				.build());
	}
	
	@PutMapping("/paciente/{id}")
	public ResponseEntity<MensajeResponse> actualizar(@Valid@PathVariable Long id,
			@Valid@RequestBody PacienteDTO pacienteDTO){
		if(!pacienteService.existePacientePorId(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(MensajeResponse.builder()
							.mensaje("Paciente con ID " + id + " no encontrado.")
							.objeto(null)
							.build());
		}
		try {
			pacienteDTO.setId(id); //esto deberia estar aca o en el service ?
			PacienteDTO pacienteActualizado = pacienteService.actualizarPaciente(id, pacienteDTO);
			return ResponseEntity.ok(MensajeResponse.builder()
					.mensaje("Paciente modificado correctamente.")
					.objeto(pacienteActualizado)
					.build());
		} catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(MensajeResponse.builder()
							.mensaje("Error al modificar paciente: " + e.getMessage())
							.objeto(null)
							.build());
		}
	}
	
	@DeleteMapping("/paciente/{id}")
	public ResponseEntity<MensajeResponse> eliminar(@Valid@PathVariable Long id){
		if(!pacienteService.existePacientePorId(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(MensajeResponse.builder()
							.mensaje("Paciente con ID " + id + " no encontrado.")
							.objeto(null)
							.build());
		}
		try {
			pacienteService.eliminarPaciente(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (DataAccessException e) {
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
					.body(MensajeResponse.builder()
							.mensaje("Error al eliminar paciente: " + e.getMessage())
							.objeto(null)
							.build());
		}
	}
	
	@GetMapping("/pacientes")
	public ResponseEntity<MensajeResponse> obtenerTodos(){
		List<PacienteDTO> listaPacientes = pacienteService.listarPacientes();
		if(listaPacientes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(MensajeResponse.builder()
							.mensaje("No se han encontrado registros.")
							.objeto(null)
							.build());
		}
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Pacientes recuperados correctamente")
				.objeto(listaPacientes)
				.build());
	}
}
