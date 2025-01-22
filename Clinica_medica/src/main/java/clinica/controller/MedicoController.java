package clinica.controller;

import java.util.List;

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
//		try {
			MedicoDTO medicoCreado = medicoService.crearMedico(medicoDTO);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(MensajeResponse.builder()
							.mensaje("Medico creado correctamente.")
							.objeto(medicoCreado)
							.build());
//		} catch (DataAccessException e) {
//        	return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
//        			.body(MensajeResponse.builder()
//        					.mensaje("Error al crear medico: " + e.getMessage())
//        					.objeto(null)
//        					.build());
//		}
	}
	
	@GetMapping("/medico/{id}")
	public ResponseEntity<MensajeResponse> obtener(@Valid@PathVariable Long id){
		MedicoDTO medicoObtenido = medicoService.obtenerMedico(id);
//		if(medicoObtenido == null) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body(MensajeResponse.builder()
//							.mensaje("Medico con ID " + id + " no encontrado.")
//							.objeto(null)
//							.build());
//		}
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Medico recuperado correctamente.")
				.objeto(medicoObtenido)
				.build());
	}
	
	@PutMapping("/medico/{id}")
	public ResponseEntity<MensajeResponse> actualizar(@Valid@PathVariable Long id,
			@Valid@RequestBody MedicoDTO medicoDTO){
//		if(!medicoService.existeMedicoPorId(id)) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body(MensajeResponse.builder()
//							.mensaje("Medico con ID " + id + " no encontrado.")
//							.objeto(null)
//							.build());
//		}
//		try {
			medicoDTO.setId(id); //esto deberia estar aca o en el service ?
			MedicoDTO medicoActualizado = medicoService.actualizarMedico(id, medicoDTO);
			return ResponseEntity.ok(MensajeResponse.builder()
					.mensaje("Medico modificado correctamente.")
					.objeto(medicoActualizado)
					.build());
//		} catch (DataAccessException e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//					.body(MensajeResponse.builder()
//							.mensaje("Error al modificar medico: " + e.getMessage())
//							.objeto(null)
//							.build());
//		}
	}
	
	@DeleteMapping("/medico/{id}")
	public ResponseEntity<MensajeResponse> eliminar(@Valid@PathVariable Long id){
//		if(!medicoService.existeMedicoPorId(id)) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body(MensajeResponse.builder()
//							.mensaje("Medico con ID " + id + " no encontrado." )
//							.objeto(null)
//							.build());
//		}
//		try {
			medicoService.eliminarMedico(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
//					.body(MensajeResponse.builder()
//							.mensaje("Error al eliminar medico: " + e.getMessage())
//							.objeto(null)
//							.build());
//		}
	}
	
	@GetMapping("/medicos")
	public ResponseEntity<MensajeResponse> obtenerTodos() {
		List<MedicoDTO> listaMedicos = medicoService.listarMedicos();
//		if(listaMedicos.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NO_CONTENT)
//					.body(MensajeResponse.builder()
//							.mensaje("No se han encontrado registros.")
//							.objeto(listaMedicos)
//							.build());
//		}
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Medicos recuperados correctamente.")
				.objeto(listaMedicos)
				.build());
	}
}
