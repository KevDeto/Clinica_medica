package clinica.service;

import clinica.model.dto.TurnoDTO;

public interface ITurnoService {
	TurnoDTO crearHorarioAtencion(Long medico_id, TurnoDTO turnoDTO);
}
