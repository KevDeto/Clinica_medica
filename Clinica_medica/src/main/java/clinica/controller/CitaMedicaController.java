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

import clinica.model.dto.CitaMedicaDTO;
import clinica.model.payload.MensajeResponse;
import clinica.service.ICitaMedicaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/")
public class CitaMedicaController {
	
    @Autowired
    private ICitaMedicaService citaMedicaService;

    @PostMapping("/cita-medica")
    public ResponseEntity<MensajeResponse> crear(@Valid@RequestBody CitaMedicaDTO citaMedicaDTO){
    	CitaMedicaDTO citaMedicaGuardada = citaMedicaService.crearCitaMedica(citaMedicaDTO);
    	return ResponseEntity.status(HttpStatus.CREATED)
				.body(MensajeResponse.builder()
						.mensaje("Cita medica creada correctamente.")
						.objeto(citaMedicaGuardada)
						.build());
    }
    
    @GetMapping("/cita-medica/{id}")
    public ResponseEntity<MensajeResponse> obtener(@Valid@PathVariable Long id){
    	CitaMedicaDTO citaMedicaObtenida = citaMedicaService.obtenerCitaMedica(id);
    	return ResponseEntity.ok(MensajeResponse.builder()
    			.mensaje("Cita medica recuperada correctamente.")
    			.objeto(citaMedicaObtenida)
    			.build());
    }
    
	@PutMapping("/cita-medica/{id}")
	public ResponseEntity<MensajeResponse> actualizar(@Valid@PathVariable Long id,
			@Valid@RequestBody CitaMedicaDTO citaMedicaDTO){
			CitaMedicaDTO citaMedicaActualizada = citaMedicaService.actualizarCitaMedica(id, citaMedicaDTO);
			return ResponseEntity.ok(MensajeResponse.builder()
					.mensaje("Cita medica modificada correctamente.")
					.objeto(citaMedicaActualizada)
					.build());
	}
	
	@DeleteMapping("/cita-medica/{id}")
	public ResponseEntity<MensajeResponse> eliminar(@Valid@PathVariable Long id){
			citaMedicaService.eliminarCitaMedica(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/citas-medicas")
	public ResponseEntity<MensajeResponse> obtenerTodos() {
		List<CitaMedicaDTO> listaCitasMedicas = citaMedicaService.listarCitasMedicas();
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Citas medicas recuperadas correctamente.")
				.objeto(listaCitasMedicas)
				.build());
	}
}
