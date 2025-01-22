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

import clinica.model.dto.MedicoDTO;
import clinica.model.payload.MensajeResponse;
import clinica.service.IMedicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class MedicoController {
	
	@Autowired
	private IMedicoService medicoService;
	
	@PostMapping("/medico")
	public ResponseEntity<MensajeResponse> crear(@Valid@RequestBody MedicoDTO medicoDTO){
			MedicoDTO medicoCreado = medicoService.crearMedico(medicoDTO);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(MensajeResponse.builder()
							.mensaje("Medico creado correctamente.")
							.objeto(medicoCreado)
							.build());
	}
	
	@GetMapping("/medico/{id}")
	public ResponseEntity<MensajeResponse> obtener(@Valid@PathVariable Long id){
		MedicoDTO medicoObtenido = medicoService.obtenerMedico(id);
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Medico recuperado correctamente.")
				.objeto(medicoObtenido)
				.build());
	}
	
	@PutMapping("/medico/{id}")
	public ResponseEntity<MensajeResponse> actualizar(@Valid@PathVariable Long id,
			@Valid@RequestBody MedicoDTO medicoDTO){
			MedicoDTO medicoActualizado = medicoService.actualizarMedico(id, medicoDTO);
			return ResponseEntity.ok(MensajeResponse.builder()
					.mensaje("Medico modificado correctamente.")
					.objeto(medicoActualizado)
					.build());
	}
	
	@DeleteMapping("/medico/{id}")
	public ResponseEntity<MensajeResponse> eliminar(@Valid@PathVariable Long id){
			medicoService.eliminarMedico(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/medicos")
	public ResponseEntity<MensajeResponse> obtenerTodos() {
		List<MedicoDTO> listaMedicos = medicoService.listarMedicos();
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Medicos recuperados correctamente.")
				.objeto(listaMedicos)
				.build());
	}
}
