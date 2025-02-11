package clinica.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import clinica.model.dto.TurnoDTO;
import clinica.model.payload.MensajeResponse;
import clinica.service.ITurnoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/")
public class TurnoController {

	@Autowired
	private ITurnoService turnoService;

	@PostMapping("/turno")
	public ResponseEntity<MensajeResponse> crear(@Valid@RequestBody TurnoDTO turnoDTO){
		TurnoDTO turnoCreado = turnoService.crearTurno(turnoDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(MensajeResponse.builder()
						.mensaje("Turno de atencion creado correctamente.")
						.objeto(turnoCreado)
						.build());
	}
	
	@GetMapping("/turno/{id}")
	public ResponseEntity<MensajeResponse> obtener(@Valid@PathVariable Long id){
		TurnoDTO turnoObtenido = turnoService.obtenerTurno(id);
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Turno obtenido correctamente.")
				.objeto(turnoObtenido)
				.build());
	}
	
	@PutMapping("/turno/{id}")
	public ResponseEntity<MensajeResponse> actualizar(@Valid@PathVariable Long id,
			@Valid@RequestBody TurnoDTO turnoDTO){
			TurnoDTO turnoActualizado = turnoService.actualizarTurno(id, turnoDTO);
			return ResponseEntity.ok(MensajeResponse.builder()
					.mensaje("Turno modificado correctamente.")
					.objeto(turnoActualizado)
					.build());
	}
	
	@DeleteMapping("/turno/{id}")
	public ResponseEntity<MensajeResponse> eliminar(@Valid@PathVariable Long id){
		turnoService.eliminarTurno(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/turnos")
	public ResponseEntity<MensajeResponse> obtenerTodos(){
		List<TurnoDTO> listaTurnos = turnoService.listaTurnos();
		return ResponseEntity.ok(MensajeResponse.builder()
				.mensaje("Turnos recuperados correctamente")
				.objeto(listaTurnos)
				.build());
	}
	
    @GetMapping("/turnos/{medicoId}/mes/año")
    public ResponseEntity<MensajeResponse> obtenerTurnosDelMes(
            @PathVariable Long medicoId,
            @RequestParam int mes,
            @RequestParam int anio) {
        
        List<LocalDate> fechas = turnoService.obtenerTurnosDelMes(medicoId, mes, anio);
        return ResponseEntity.ok(MensajeResponse.builder()
        		.mensaje("Turnos disponibles recuperados correctamente.")
        		.objeto(fechas)
        		.build());
    }
}
