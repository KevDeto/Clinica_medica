package clinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import clinica.model.dto.CitaMedicaDTO;
import clinica.model.dto.TurnoDTO;
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
    	return ResponseEntity.ok(MensajeResponse.builder()
    			.mensaje("Cita medica creada correctamente.")
    			.objeto(citaMedicaGuardada)
    			.build());
    }
}
