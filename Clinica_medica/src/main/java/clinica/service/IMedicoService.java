package clinica.service;

import java.util.List;

import clinica.model.dto.MedicoDTO;

public interface IMedicoService {
	MedicoDTO crearMedico(MedicoDTO medicoDTO);
	MedicoDTO obtenerMedico(Long id);
	MedicoDTO actualizarMedico(Long id, MedicoDTO medicoDTO);
	void eliminarMedico(Long id);
	List<MedicoDTO> listarMedicos();
}
