package clinica.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//	@Autowired
//	private IMedicoRepository medicoRepository;
    @Autowired
    private ITurnoMapper turnoMapper;

    @Transactional
	@Override
	public TurnoDTO crearTurno(TurnoDTO turnoDTO) {
	    if (turnoDTO.getHora_inicio().isAfter(turnoDTO.getHora_fin())) {
	        throw new ApplicationException(ErrorCode.BAD_REQUEST,
	        		"La hora de inicio no puede ser después de la hora de fin", null);
	    }
//	    if(medicoRepository.existsById(turnoDTO.getMedico_id())) {
//	    	throw new ApplicationException(ErrorCode.NOT_FOUND,
//            		"El medico con ID " + turnoDTO.getMedico_id() + " no existe.", null);
//	    }
	    try {
	        Turno turnoMapeado = turnoMapper.deTurnoDTOATurno(turnoDTO);
	        return turnoMapper.deTurnoATurnoDTO(turnoRepository.save(turnoMapeado));
	    }catch(Exception ex) {
	        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, 
	                "Error desconocido al guardar el turno disponible.", ex);
	    }
	}

	@Override
	public TurnoDTO obtenerTurno(Long id) {
		Optional<Turno> optionalTurno = turnoRepository.findById(id);
		
		if(optionalTurno.isEmpty()) {
			throw new ApplicationException(ErrorCode.NOT_FOUND,
					"Turno con ID " + id + " no existe.", null);
		}
		Turno turno = optionalTurno.get();
		TurnoDTO turnoDtoMapeado = turnoMapper.deTurnoATurnoDTO(turno);
		return turnoDtoMapeado;
	}

	@Transactional
	@Override
	public TurnoDTO actualizarTurno(Long id, TurnoDTO turnoDTO) {
		Turno turno = turnoRepository.findById(id)
				.orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,
						"Turno con ID " + id + " no existe.", null));
	    
//		if(medicoRepository.existsById(turnoDTO.getMedico_id())) {
//	    	throw new ApplicationException(ErrorCode.NOT_FOUND,
//            		"El medico con ID " + turnoDTO.getMedico_id() + " no existe.", null);
//	    }
		try {
			turno.setId(id);
			turnoMapper.actualizarDeTurnoATurnoDTO(turno, turnoDTO);
			turnoRepository.save(turno);
			return turnoMapper.deTurnoATurnoDTO(turno);
		} catch (DataIntegrityViolationException ex) {
	        throw new ApplicationException(ErrorCode.CONFLICT, 
	                "Error de integridad de datos: existen duplicados.", ex); 
		} catch (Exception ex) {
	        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, 
	                "Error interno del servidor.", ex);
		}
	}

	@Override
	public void eliminarTurno(Long id) {
		if(!turnoRepository.existsById(id)) {
			throw new ApplicationException(ErrorCode.NOT_FOUND,
					"Turno con ID " + id + " no existe.", null);
		}
	}

	@Override
	public List<TurnoDTO> listaTurnos() {
		List<Turno> listaTurnos = turnoRepository.findAll();
	
		return listaTurnos.stream()
				.map(turnoMapper::deTurnoATurnoDTO)
				.collect(Collectors.toList());
	}
}