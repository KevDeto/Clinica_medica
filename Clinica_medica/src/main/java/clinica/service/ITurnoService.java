package clinica.service;

import java.time.LocalDate;
import java.util.List;

import clinica.model.dto.TurnoDTO;

public interface ITurnoService {
	TurnoDTO crearTurno(TurnoDTO turnoDTO);
	TurnoDTO obtenerTurno(Long id);
	TurnoDTO actualizarTurno(Long id, TurnoDTO turnoDTO);
	void eliminarTurno(Long id);
	List<TurnoDTO> listaTurnos();
	List<LocalDate> obtenerTurnosDelMes(Long medicoId, int anio, int mes);
}
