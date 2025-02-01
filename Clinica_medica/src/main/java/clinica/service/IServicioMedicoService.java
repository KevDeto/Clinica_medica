package clinica.service;

import java.util.List;

import clinica.model.dto.ServicioMedicoDTO;

public interface IServicioMedicoService {
	ServicioMedicoDTO crearServicioMedico(ServicioMedicoDTO servicioMedicoDTO);
	ServicioMedicoDTO obtenerServicioMedico(Long id);
	ServicioMedicoDTO actualizarServicioMedico(Long id, ServicioMedicoDTO servicioMedicoDTO);
	void eliminarServicioMedico(Long id);
	List<ServicioMedicoDTO> listaServicioMedico();
}
