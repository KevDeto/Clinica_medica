package clinica.service.impl;

import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinica.exceptions.ApplicationException;
import clinica.exceptions.ErrorCode;
import clinica.model.dao.IMedicoRepository;
import clinica.model.dao.ITurnoRepository;
import clinica.model.dto.TurnoDTO;
import clinica.model.entity.Medico;
import clinica.model.entity.Turno;
import clinica.model.mapper.ITurnoMapper;
import clinica.service.ITurnoService;

@Service
public class TurnoServiceImpl implements ITurnoService{
	
	@Autowired
	private ITurnoRepository turnoRepository;
	@Autowired
	private IMedicoRepository medicoRepository;
    @Autowired
    private ITurnoMapper turnoMapper;

	@Override
	public TurnoDTO crearTurno(TurnoDTO turnoDTO) {
	    if (turnoDTO.getHora_inicio().isAfter(turnoDTO.getHora_fin())) {
	        throw new ApplicationException(ErrorCode.BAD_REQUEST,
	        		"La hora de inicio no puede ser después de la hora de fin", null);
	    }
	    try {
	    	System.out.println(turnoDTO.toString());
	        Turno mapperTurno = turnoMapper.deTurnoDTOATurno(turnoDTO, medicoRepository);
	        return turnoMapper.deTurnoATurnoDTO(turnoRepository.save(mapperTurno));
	    }catch(Exception ex) {
	        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, 
	                "Error desconocido al guardar el horario disponible.", ex);
	    }
	}
}
