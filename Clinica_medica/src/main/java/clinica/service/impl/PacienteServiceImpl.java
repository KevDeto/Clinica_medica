package clinica.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinica.exceptions.ApplicationException;
import clinica.exceptions.ErrorCode;
import clinica.model.dao.IPacienteRepository;
import clinica.model.dto.PacienteDTO;
import clinica.model.entity.Paciente;
import clinica.model.mapper.IPacienteMapper;
import clinica.service.IPacienteService;

@Service
public class PacienteServiceImpl implements IPacienteService{
	
	@Autowired
	private IPacienteRepository pacienteRepository;

	@Transactional
	@Override
	public PacienteDTO crearPaciente(PacienteDTO pacienteDTO){
		try {
			Paciente mapperPaciente = IPacienteMapper.INSTANCE.dePacienteDTOAPaciente(pacienteDTO);
			pacienteRepository.save(mapperPaciente);
			PacienteDTO mapperPacienteDTO = IPacienteMapper.INSTANCE.dePacienteAPacienteDTO(mapperPaciente);
			
			return mapperPacienteDTO;
		} catch (DataIntegrityViolationException ex) {
	        throw new ApplicationException(ErrorCode.CONFLICT, 
	                "Error de integridad de datos: existen duplicados.", ex);
	    } catch (Exception ex) {
	        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, 
	                "Error interno del servidor.", ex);
		}
	}

	@Override
	public PacienteDTO obtenerPaciente(Long id) {
		Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
		
		if(optionalPaciente.isEmpty()) {
			throw new ApplicationException(ErrorCode.NOT_FOUND,
					"Paciente con ID " + id + " no existe.", null);
		}
		Paciente paciente = optionalPaciente.get();
		PacienteDTO mapperPacienteDTO = IPacienteMapper.INSTANCE.dePacienteAPacienteDTO(paciente);
		return mapperPacienteDTO;
	}

	@Override
	public PacienteDTO actualizarPaciente(Long id, PacienteDTO pacienteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarPaciente(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existePacientePorId(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PacienteDTO> listarPacientes() {
		// TODO Auto-generated method stub
		return null;
	}

}
