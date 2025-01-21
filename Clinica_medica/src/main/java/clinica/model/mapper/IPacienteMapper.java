package clinica.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import clinica.model.dto.PacienteDTO;
import clinica.model.entity.Paciente;

//utilizar un mapper personalisado para que no choque entre IDs
@Mapper(componentModel = "spring")
public interface IPacienteMapper {
	IPacienteMapper INSTANCE = Mappers.getMapper(IPacienteMapper.class);
	PacienteDTO dePacienteAPacienteDTO(Paciente paciente);
	Paciente dePacienteDTOAPaciente(PacienteDTO pacienteDTO);
	List<PacienteDTO> deListaPacienteAListaPacienteDTO(List<Paciente> paciente);
	List<Paciente> deListaPacienteDTOAListaPaciente(List<PacienteDTO> pacienteDTO);
}
