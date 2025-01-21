package clinica.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public PacienteDTO crearPaciente(PacienteDTO pacienteDTO) {
		Paciente mapperPaciente = IPacienteMapper.INSTANCE.dePacienteDTOAPaciente(pacienteDTO);
		pacienteRepository.save(mapperPaciente);	
		PacienteDTO mapperPacienteDTO = IPacienteMapper.INSTANCE.dePacienteAPacienteDTO(mapperPaciente);
		
		return mapperPacienteDTO;
	}

	@Override
	public PacienteDTO obtenerPaciente(Long id) {
		Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
		
		if(optionalPaciente.isEmpty()) {
			throw new RuntimeException("Paciente con ID " + id + " no encontrado.");
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
