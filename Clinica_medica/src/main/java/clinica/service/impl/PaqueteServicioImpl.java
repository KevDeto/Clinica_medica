package clinica.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinica.exceptions.ApplicationException;
import clinica.exceptions.ErrorCode;
import clinica.model.dao.IPaqueteMedicoRepository;
import clinica.model.dto.PaqueteServicioDTO;
import clinica.model.entity.PaqueteServicio;
import clinica.model.mapper.IPaqueteMapper;
import clinica.service.IPaqueteServicioService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaqueteServicioImpl implements IPaqueteServicioService{

	private IPaqueteMedicoRepository paqueteMedicoRepository;
	private IPaqueteMapper paqueteMapper;

	@Transactional
	@Override
	public PaqueteServicioDTO crearPaqueteServicio(PaqueteServicioDTO paqueteServicioDTO) {
		try {
			
			PaqueteServicio paqueteMapeado = paqueteMapper.dePaqueteServicioDTOAPaqueteServicio(paqueteServicioDTO);
			paqueteMedicoRepository.save(paqueteMapeado);
			PaqueteServicioDTO paqueteDtoMapeado = paqueteMapper.dePaqueteServicioAPaqueteServicioDTO(paqueteMapeado);
			
			return paqueteDtoMapeado;
	    } catch (DataIntegrityViolationException ex) {
	        throw new ApplicationException(ErrorCode.CONFLICT, 
	                "Error de integridad de datos: existen duplicados.", ex);
		} catch (Exception ex) {
	        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, 
	                "Error desconocido al guardar el paquete medico.", ex);
	    }
	}

	@Override
	public PaqueteServicioDTO obtenerPaqueteServicio(Long id) {
		Optional<PaqueteServicio> paqueteServicioObtenido = paqueteMedicoRepository.findById(id);
		
		if(paqueteServicioObtenido.isEmpty()) {
			throw new ApplicationException(ErrorCode.NOT_FOUND,
					"Paquete con ID " + id + " no existe.", null);
		}
		PaqueteServicio paquete = paqueteServicioObtenido.get();
		PaqueteServicioDTO paqueteDtoMapper = paqueteMapper.dePaqueteServicioAPaqueteServicioDTO(paquete);
		return paqueteDtoMapper;
	}

	@Transactional
	@Override
	public PaqueteServicioDTO actualizarPaqueteServicio(Long id, PaqueteServicioDTO paqueteServicioDTO) {
	    PaqueteServicio paqueteServicio = paqueteMedicoRepository.findById(id)
	            .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,
	                    "Paquete con ID " + id + " no encontrado.", null));
		try {
			paqueteServicioDTO.setCodigoPaquete(id);
			paqueteMapper.actualizarPaqueteServicioDesdeDTO(paqueteServicio, paqueteServicioDTO);
			paqueteMedicoRepository.save(paqueteServicio);
			PaqueteServicioDTO paqueteDtoMapeado = paqueteMapper.dePaqueteServicioAPaqueteServicioDTO(paqueteServicio);
			return paqueteDtoMapeado;
	    } catch (DataIntegrityViolationException ex) {
	        throw new ApplicationException(ErrorCode.CONFLICT, 
	                "Error de integridad de datos: existen duplicados.", ex);
		} catch (Exception ex) {
	        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, 
	                "Error interno del servidor.", ex);
	    }
	}

	@Override
	public void eliminarPaqueteServicio(Long id) {
		if(!paqueteMedicoRepository.existsById(id)) {
			throw new ApplicationException(ErrorCode.NOT_FOUND,
					"Paquete con ID " + id + " no existe.", null);
		}
		paqueteMedicoRepository.deleteById(id);
	}

	@Override
	public List<PaqueteServicioDTO> listaPaqueteServicio() {
		List<PaqueteServicio> listaPaquetes = paqueteMedicoRepository.findAll();
        return listaPaquetes.stream()
                .map(paqueteMapper::dePaqueteServicioAPaqueteServicioDTO)
                .collect(Collectors.toList());
	}

}
