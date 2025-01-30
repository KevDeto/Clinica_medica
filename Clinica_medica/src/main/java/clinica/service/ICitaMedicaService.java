package clinica.service;

import java.util.List;

import clinica.model.dto.CitaMedicaDTO;

public interface ICitaMedicaService {
	CitaMedicaDTO crearCitaMedica(CitaMedicaDTO citaMedicaDTO);
	CitaMedicaDTO obtenerCitaMedica(Long id);
	CitaMedicaDTO actualizarCitaMedica(Long id, CitaMedicaDTO citaMedicaDTO);
	void eliminarCitaMedica(Long id);
	List<CitaMedicaDTO> listarCitasMedicas();
}
