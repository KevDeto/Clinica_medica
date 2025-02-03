package clinica.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinica.exceptions.ApplicationException;
import clinica.exceptions.ErrorCode;
import clinica.model.dao.IServicioMedicoRepository;
import clinica.model.dto.ServicioMedicoDTO;
import clinica.model.entity.ServicioMedico;
import clinica.model.mapper.IServicioMapper;
import clinica.service.IServicioMedicoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioMedicoImpl implements IServicioMedicoService {

	private IServicioMedicoRepository servicioMedicoRepository;
	private IServicioMapper servicioMapper;

	@Transactional
	@Override
	public ServicioMedicoDTO crearServicioMedico(ServicioMedicoDTO servicioMedicoDTO) {
		try {

			ServicioMedico servicioMapeado = servicioMapper.deServicioMedicoDTOAServicioMedico(servicioMedicoDTO);
			servicioMedicoRepository.save(servicioMapeado);
			ServicioMedicoDTO servicioDtoMapeado = servicioMapper.deServicioMedicoAServicioMedicoDTO(servicioMapeado);

			return servicioDtoMapeado;
		} catch (DataIntegrityViolationException ex) {
			throw new ApplicationException(ErrorCode.CONFLICT, "Error de integridad de datos: existen duplicados.", ex);
		} catch (Exception ex) {
			throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR,
					"Error desconocido al guardar el paquete medico.", ex);
		}
	}

	@Override
	public ServicioMedicoDTO obtenerServicioMedico(Long id) {
		Optional<ServicioMedico> servicioMedicoObtenido = servicioMedicoRepository.findById(id);
		
		if(servicioMedicoObtenido.isEmpty()) {
			throw new ApplicationException(ErrorCode.NOT_FOUND,
					"Servicio con ID " + id + " no existe.", null);
		}
		ServicioMedico servicio = servicioMedicoObtenido.get();
		ServicioMedicoDTO servicioDtoMapeado = servicioMapper.deServicioMedicoAServicioMedicoDTO(servicio);
		return servicioDtoMapeado;
	}

	@Transactional
	@Override
	public ServicioMedicoDTO actualizarServicioMedico(Long id, ServicioMedicoDTO servicioMedicoDTO) {
		ServicioMedico servicioMedico = servicioMedicoRepository.findById(id)
	            .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,
	                    "Servicio con ID " + id + " no encontrado.", null));
		try {
			servicioMedicoDTO.setCodigoServicio(id);
			servicioMapper.actualizarServicioMedicoDesdeDTO(servicioMedico, servicioMedicoDTO);
			servicioMedicoRepository.save(servicioMedico);
			ServicioMedicoDTO servicioDtoMapeado = servicioMapper.deServicioMedicoAServicioMedicoDTO(servicioMedico);
			return servicioDtoMapeado;
	    } catch (DataIntegrityViolationException ex) {
	        throw new ApplicationException(ErrorCode.CONFLICT, 
	                "Error de integridad de datos: existen duplicados.", ex);
		} catch (Exception ex) {
	        throw new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR, 
	                "Error interno del servidor.", ex);
	    }
	}

	@Override
	public void eliminarServicioMedico(Long id) {
		if(!servicioMedicoRepository.existsById(id)) {
			throw new ApplicationException(ErrorCode.NOT_FOUND,
					"Servicio con ID " + id + " no existe.", null);
		}
		servicioMedicoRepository.deleteById(id);
	}

	@Override
	public List<ServicioMedicoDTO> listaServicioMedico() {
		List<ServicioMedico> listaPaquetes = servicioMedicoRepository.findAll();
        return listaPaquetes.stream()
                .map(servicioMapper::deServicioMedicoAServicioMedicoDTO)
                .collect(Collectors.toList());
	}

}
