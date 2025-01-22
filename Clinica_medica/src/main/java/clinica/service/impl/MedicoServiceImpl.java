package clinica.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinica.model.dao.IMedicoRepository;
import clinica.model.dto.MedicoDTO;
import clinica.model.entity.Medico;
import clinica.model.mapper.IMedicoMapper;
import clinica.service.IMedicoService;

@Service
public class MedicoServiceImpl implements IMedicoService {

	@Autowired
	private IMedicoRepository medicoRepository;

	@Transactional
	@Override
	public MedicoDTO crearMedico(MedicoDTO medicoDTO) {
		Medico mapperMedico = IMedicoMapper.INSTANCE.deMedicoDTOAMedico(medicoDTO);
		medicoRepository.save(mapperMedico);
		MedicoDTO mapperMedicoDTO = IMedicoMapper.INSTANCE.deMedicoAMedicoDTO(mapperMedico);
		
		return mapperMedicoDTO;
	}

	@Override
	public MedicoDTO obtenerMedico(Long id) {
		Optional<Medico> medicoObtenido = medicoRepository.findById(id);
		
		if(medicoObtenido.isEmpty()) {
			throw new RuntimeException("Paciente con ID " + id + " no encontrado.");
		}
		Medico medico = medicoObtenido.get();
		MedicoDTO mapperMedicoDTO = IMedicoMapper.INSTANCE.deMedicoAMedicoDTO(medico);
		
		return mapperMedicoDTO;
	}

	@Override
	public MedicoDTO actualizarMedico(Long id, MedicoDTO medicoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarMedico(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existeMedicoPorId(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MedicoDTO> listarMedicos() {
		// TODO Auto-generated method stub
		return null;
	}

}
