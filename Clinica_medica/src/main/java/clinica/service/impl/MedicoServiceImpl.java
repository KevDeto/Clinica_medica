package clinica.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinica.exceptions.ApplicationException;
import clinica.exceptions.ErrorCode;
import clinica.model.dao.IMedicoRepository;
import clinica.model.dto.MedicoDTO;
import clinica.model.entity.Medico;
import clinica.model.mapper.IMedicoMapper;
import clinica.service.IMedicoService;

@Service
public class MedicoServiceImpl implements IMedicoService {

	@Autowired
	private IMedicoRepository medicoRepository;
	@Autowired
	private IMedicoMapper medicoMapper;

	@Transactional
	@Override
	public MedicoDTO crearMedico(MedicoDTO medicoDTO) {
		try {
			
			Medico medicoMapeado = medicoMapper.deMedicoDTOAMedico(medicoDTO);
			medicoRepository.save(medicoMapeado);
			MedicoDTO medicoDtoMapeado = medicoMapper.deMedicoAMedicoDTO(medicoMapeado);
			
			return medicoDtoMapeado;
	    } catch (DataIntegrityViolationException ex) {
	        throw new ApplicationException(ErrorCode.CONFLICT, 
	                "Error de integridad de datos: existen duplicados.", ex);
		} catch (Exception ex) {
	        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, 
	                "Error desconocido al guardar el medico.", ex);
	    }
	}

	@Override
	public MedicoDTO obtenerMedico(Long id) {
		Optional<Medico> medicoObtenido = medicoRepository.findById(id);
		
		if(medicoObtenido.isEmpty()) {
			throw new ApplicationException(ErrorCode.NOT_FOUND,
					"Paciente con ID " + id + " no existe.", null);
		}
		Medico medico = medicoObtenido.get();
		MedicoDTO medicoDtoMapeado = medicoMapper.deMedicoAMedicoDTO(medico);
		return medicoDtoMapeado;
	}

	@Transactional
	@Override
	public MedicoDTO actualizarMedico(Long id, MedicoDTO medicoDTO) {
	    Medico medico = medicoRepository.findById(id)
	            .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,
	                    "Medico con ID " + id + " no encontrado.", null));
		try {
			medicoDTO.setId(id);
			medicoMapper.actualizarDeMedicoAMedicoDTO(medico, medicoDTO);
			medicoRepository.save(medico);
			MedicoDTO medicoDtoMapeado = medicoMapper.deMedicoAMedicoDTO(medico);
			return medicoDtoMapeado;
	    } catch (DataIntegrityViolationException ex) {
	        throw new ApplicationException(ErrorCode.CONFLICT, 
	                "Error de integridad de datos: existen duplicados.", ex);
		} catch (Exception ex) {
	        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, 
	                "Error interno del servidor.", ex);
	    }
	}

	@Override
	public void eliminarMedico(Long id) {
		if(!medicoRepository.existsById(id)) {
			throw new ApplicationException(ErrorCode.NOT_FOUND,
					"Paciente con ID " + id + " no existe.", null);
		}
		medicoRepository.deleteById(id);
	}

	@Override
	public boolean existeMedicoPorId(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MedicoDTO> listarMedicos() {
		List<Medico> listaMedicos = medicoRepository.findAll();
        return listaMedicos.stream()
                .map(medicoMapper::deMedicoAMedicoDTO)
                .collect(Collectors.toList());
	}
}
