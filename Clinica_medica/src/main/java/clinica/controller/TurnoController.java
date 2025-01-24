package clinica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import clinica.model.dto.TurnoDTO;
import clinica.model.payload.MensajeResponse;
import clinica.service.ITurnoService;
import jakarta.validation.Valid;
//suponiendo que el rol es del Medico
@RestController
@RequestMapping("api/v1/")
public class TurnoController {

	@Autowired
	private ITurnoService turnoService;
	
	@PostMapping("horario-atencion/{medico_id}")
	public ResponseEntity<MensajeResponse> crear(@Valid@RequestBody TurnoDTO turnoDTO){
		TurnoDTO turnoCreado = turnoService.crearTurno(turnoDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(MensajeResponse.builder()
						.mensaje("Horario de atencion creado correctamente.")
						.objeto(turnoCreado)
						.build());
	}
}
