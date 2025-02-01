package clinica.service;

import java.util.List;

import clinica.model.dto.PaqueteServicioDTO;

public interface IPaqueteServicioService {
	PaqueteServicioDTO crearPaqueteServicio(PaqueteServicioDTO paqueteServicioDTO);
	PaqueteServicioDTO obtenerPaqueteServicio(Long id);
	PaqueteServicioDTO actualizarPaqueteServicio(Long id, PaqueteServicioDTO paqueteServicioDTO);
	void eliminarPaqueteServicio(Long id);
	List<PaqueteServicioDTO> listaPaqueteServicio();
}
