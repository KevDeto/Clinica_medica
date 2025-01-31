package clinica.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import clinica.exceptions.ApplicationException;
import clinica.exceptions.ErrorCode;

import clinica.model.dao.ICitaMedicaRepository;
import clinica.model.dto.CitaMedicaDTO;
import clinica.model.entity.CitaMedica;
import clinica.model.mapper.ICitaMedicaMapper;

import clinica.service.ICitaMedicaService;
import jakarta.transaction.Transactional;

@Service
public class CitaMedicaServiceImpl implements ICitaMedicaService{

	@Autowired
	private ICitaMedicaRepository citaMedicaRepository;
	@Autowired
	private ICitaMedicaMapper citaMedicaMapper;
	
	@Override
	public CitaMedicaDTO crearCitaMedica(CitaMedicaDTO citaMedicaDTO) {
		try {
			CitaMedica citaMedicaMapeado = citaMedicaMapper.deCitaMedicaDTOACitaMedica(citaMedicaDTO);
			citaMedicaRepository.save(citaMedicaMapeado);
			return citaMedicaMapper.deCitaMedicaACitaMedicaDTO(citaMedicaMapeado);
		} catch (DataIntegrityViolationException ex) {
	        throw new ApplicationException(ErrorCode.CONFLICT, 
	                "Error de integridad de datos: existen duplicados.", ex);
		} catch (Exception ex) {
	        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, 
	                "Error desconocido al guardar el medico.", ex);
	    }
	}

	@Override
	public CitaMedicaDTO obtenerCitaMedica(Long id) {
		Optional<CitaMedica> optionalCitaMedica = citaMedicaRepository.findById(id);
		if(optionalCitaMedica.isEmpty()) {
			throw new ApplicationException(ErrorCode.NOT_FOUND,
					"Cita medica con ID " + id + " no existe.", null);
		}
		CitaMedica citaMedica = optionalCitaMedica.get();
		CitaMedicaDTO turnoDtoMapeado = citaMedicaMapper.deCitaMedicaACitaMedicaDTO(citaMedica);
		return turnoDtoMapeado;
	}

	@Transactional
	@Override
	public CitaMedicaDTO actualizarCitaMedica(Long id, CitaMedicaDTO citaMedicaDTO) {
		CitaMedica citaMedica = citaMedicaRepository.findById(id)
				.orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,
						"Cita medica con ID " + id + " no existe.", null));
		try {
			citaMedica.setId(id);
			citaMedicaMapper.actualizarDeCitaMedicaACitaMedicaDTO(citaMedica, citaMedicaDTO);
			citaMedicaRepository.save(citaMedica);
			return citaMedicaMapper.deCitaMedicaACitaMedicaDTO(citaMedica);
		} catch (DataIntegrityViolationException ex) {
	        throw new ApplicationException(ErrorCode.CONFLICT, 
	                "Error de integridad de datos: existen duplicados.", ex); 
		} catch (Exception ex) {
	        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, 
	                "Error interno del servidor.", ex);
		}
	}

	@Override
	public void eliminarCitaMedica(Long id) {
		if(!citaMedicaRepository.existsById(id)) {
			throw new ApplicationException(ErrorCode.NOT_FOUND,
					"Cita medica con ID " + id + " no existe.", null);
		}
		citaMedicaRepository.deleteById(id);
	}

	@Override
	public List<CitaMedicaDTO> listarCitasMedicas() {
		List<CitaMedica> listaTurnos = citaMedicaRepository.findAll();
		return listaTurnos.stream()
				.map(citaMedicaMapper::deCitaMedicaACitaMedicaDTO)
				.collect(Collectors.toList());
	}

}
