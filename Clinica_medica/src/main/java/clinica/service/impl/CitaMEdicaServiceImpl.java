package clinica.service.impl;

import java.util.List;

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

@Service
public class CitaMEdicaServiceImpl implements ICitaMedicaService{

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CitaMedicaDTO actualizarCitaMedica(Long id, CitaMedicaDTO citaMedicaDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarCitaMedica(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CitaMedicaDTO> listarCitasMedicas() {
		// TODO Auto-generated method stub
		return null;
	}

}
