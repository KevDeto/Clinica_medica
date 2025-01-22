package clinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
			PacienteDTO pacienteCreado = pacienteService.crearPaciente(pacienteDTO);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(MensajeResponse.builder()
							.mensaje("Paciente creado correctamente.")
							.objeto(pacienteCreado)
							.build());	
	}
	
	@GetMapping("/paciente/{id}")
	public ResponseEntity<MensajeResponse> obtener(@Valid@PathVariable Long id){
		PacienteDTO pacienteObtenido = pacienteService.obtenerPaciente(id);
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Paciente obtenido correctamente.")
				.objeto(pacienteObtenido)
				.build());
	}
	
	@PutMapping("/paciente/{id}")
	public ResponseEntity<MensajeResponse> actualizar(@Valid@PathVariable Long id,
			@Valid@RequestBody PacienteDTO pacienteDTO){
			PacienteDTO pacienteActualizado = pacienteService.actualizarPaciente(id, pacienteDTO);
			return ResponseEntity.ok(MensajeResponse.builder()
					.mensaje("Paciente modificado correctamente.")
					.objeto(pacienteActualizado)
					.build());
	}
	
	@DeleteMapping("/paciente/{id}")
	public ResponseEntity<MensajeResponse> eliminar(@Valid@PathVariable Long id){
		pacienteService.eliminarPaciente(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/pacientes")
	public ResponseEntity<MensajeResponse> obtenerTodos(){
		List<PacienteDTO> listaPacientes = pacienteService.listarPacientes();
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Pacientes recuperados correctamente")
				.objeto(listaPacientes)
				.build());
	}
}
