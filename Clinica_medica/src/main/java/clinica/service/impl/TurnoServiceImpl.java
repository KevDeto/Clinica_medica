package clinica.service.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinica.exceptions.ApplicationException;
import clinica.exceptions.ErrorCode;
import clinica.model.dao.ITurnoRepository;
import clinica.model.dto.TurnoDTO;
import clinica.model.entity.EstadoTurno;
import clinica.model.entity.Turno;
import clinica.model.mapper.ITurnoMapper;
import clinica.service.ITurnoService;

@Service
public class TurnoServiceImpl implements ITurnoService{
	
	@Autowired
	private ITurnoRepository turnoRepository;

	@Override
	public TurnoDTO crearHorarioAtencion(Long medico_id, TurnoDTO turnoDTO) {
	    if (turnoDTO.getHora_inicio().isAfter(turnoDTO.getHora_fin())) {
	        throw new ApplicationException(ErrorCode.BAD_REQUEST,
	        		"La hora de inicio no puede ser después de la hora de fin", null);
	    }
	    try {
	        for (LocalTime hora = turnoDTO.getHora_inicio(); hora.isBefore(turnoDTO.getHora_fin()); hora = hora.plusMinutes(5)) {
	            TurnoDTO turnoCreado = new TurnoDTO();
	            turnoCreado.setFecha(turnoDTO.getFecha());
	            turnoCreado.setHora_inicio(turnoDTO.getHora_inicio());
	            turnoCreado.setHora_fin(hora.plusMinutes(5));
	            turnoCreado.setMedico_id(medico_id);
	            turnoCreado.setEstado(EstadoTurno.DISPONIBLE);
	            
	            Turno mapperTurno = ITurnoMapper.INSTANCE.deTurnoDTOATurno(turnoDTO);
	            
	            turnoRepository.save(mapperTurno);
	        }
			return turnoDTO;
	    }catch(Exception ex) {
	        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, 
	                "Error desconocido al guardar el horario disponible.", ex);
	    }
	}
}
