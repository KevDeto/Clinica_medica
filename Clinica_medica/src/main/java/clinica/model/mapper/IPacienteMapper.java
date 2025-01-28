package clinica.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import clinica.model.dto.MedicoDTO;
import clinica.model.dto.PacienteDTO;
import clinica.model.entity.Medico;
import clinica.model.entity.Paciente;

//utilizar un mapper personalisado para que no choque entre IDs
@Mapper(componentModel = "spring")
public interface IPacienteMapper {
	PacienteDTO dePacienteAPacienteDTO(Paciente paciente);
	Paciente dePacienteDTOAPaciente(PacienteDTO pacienteDTO);
	List<PacienteDTO> deListaPacienteAListaPacienteDTO(List<Paciente> paciente);
	List<Paciente> deListaPacienteDTOAListaPaciente(List<PacienteDTO> pacienteDTO);
	void actualizarPacienteDesdeDTO(@MappingTarget Paciente paciente, PacienteDTO pacienteDTO);
}
