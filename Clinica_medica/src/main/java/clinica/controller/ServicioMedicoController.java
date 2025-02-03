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

import clinica.model.dto.ServicioMedicoDTO;
import clinica.model.payload.MensajeResponse;
import clinica.service.IServicioMedicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ServicioMedicoController {

	@Autowired
	private IServicioMedicoService servicioMedicoService;
	
	@PostMapping("/servicio")
	public ResponseEntity<MensajeResponse> crear(@Valid@RequestBody ServicioMedicoDTO servicioMedicoDTO){
		ServicioMedicoDTO servicioMedico = servicioMedicoService.crearServicioMedico(servicioMedicoDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(MensajeResponse.builder()
						.mensaje("Servicio medico registrado correctamente.")
						.objeto(servicioMedico)
						.build());
	}
	
	@GetMapping("/servicio/{id}")
	public ResponseEntity<MensajeResponse> obtener(@Valid@PathVariable Long id){
		ServicioMedicoDTO servicioMedico = servicioMedicoService.obtenerServicioMedico(id);
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Servicio medico recuperado correctamente.")
				.objeto(servicioMedico)
				.build());
	}
	
	@PutMapping("/servicio/{id}")
	public ResponseEntity<MensajeResponse> actualizar(@Valid@PathVariable Long id,
			@Valid@RequestBody ServicioMedicoDTO servicioMedicoDTO){
		ServicioMedicoDTO servicioMedico = servicioMedicoService.actualizarServicioMedico(id, servicioMedicoDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(MensajeResponse.builder()
						.mensaje("Servicio medico actualizado correctamente.")
						.objeto(servicioMedico)
						.build());
	}
	
	@DeleteMapping("/servicio/{id}")
	public ResponseEntity<MensajeResponse> eliminar(@Valid@PathVariable Long id){
		servicioMedicoService.eliminarServicioMedico(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/servicios")
	public ResponseEntity<MensajeResponse> obtenerTodos(){
		List<ServicioMedicoDTO> listaServiciosMedicos = servicioMedicoService.listaServicioMedico();
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Servicios medicos recuperados correctamente.")
				.objeto(listaServiciosMedicos)
				.build());
	}
}
