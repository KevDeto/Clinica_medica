package clinica.service;

import java.util.List;

import clinica.model.dto.PacienteDTO;

public interface IPacienteService {
	PacienteDTO crearPaciente(PacienteDTO pacienteDTO);
	PacienteDTO obtenerPaciente(Long id);
	PacienteDTO actualizarPaciente(Long id, PacienteDTO pacienteDTO);
	void eliminarPaciente(Long id);
	boolean existePacientePorId(Long id);
	List<PacienteDTO> listarPacientes();
}
