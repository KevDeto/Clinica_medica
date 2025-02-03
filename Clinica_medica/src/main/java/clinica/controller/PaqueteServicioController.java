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

import clinica.model.dto.PaqueteServicioDTO;
import clinica.model.dto.ServicioMedicoDTO;
import clinica.model.payload.MensajeResponse;
import clinica.service.IPaqueteServicioService;
import clinica.service.IServicioMedicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class PaqueteServicioController {

	@Autowired
	private IPaqueteServicioService paqueteServicioService;
	
	@PostMapping("/paquete")
	public ResponseEntity<MensajeResponse> crear(@Valid@RequestBody PaqueteServicioDTO paqueteServicioDTO){
		PaqueteServicioDTO paqueteServicio = paqueteServicioService.crearPaqueteServicio(paqueteServicioDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(MensajeResponse.builder()
						.mensaje("Paquete registrado correctamente.")
						.objeto(paqueteServicio)
						.build());
	}
	
	@GetMapping("/paquete/{id}")
	public ResponseEntity<MensajeResponse> obtener(@Valid@PathVariable Long id){
		PaqueteServicioDTO paqueteServicio = paqueteServicioService.obtenerPaqueteServicio(id);
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Paquete recuperado correctamente.")
				.objeto(paqueteServicio)
				.build());
	}
	
	@PutMapping("/paquete/{id}")
	public ResponseEntity<MensajeResponse> actualizar(@Valid@PathVariable Long id,
			@Valid@RequestBody PaqueteServicioDTO paqueteServicioDTO){
		PaqueteServicioDTO paqueteServicio = paqueteServicioService.actualizarPaqueteServicio(id, paqueteServicioDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(MensajeResponse.builder()
						.mensaje("Paquete actualizado correctamente.")
						.objeto(paqueteServicio)
						.build());
	}
	
	@DeleteMapping("/paquete/{id}")
	public ResponseEntity<MensajeResponse> eliminar(@Valid@PathVariable Long id){
		paqueteServicioService.eliminarPaqueteServicio(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/paquetes")
	public ResponseEntity<MensajeResponse> obtenerTodos(){
		List<PaqueteServicioDTO> listarPaquetes = paqueteServicioService.listaPaqueteServicio();
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Paquetes recuperados correctamente.")
				.objeto(listarPaquetes)
				.build());
	}
}
