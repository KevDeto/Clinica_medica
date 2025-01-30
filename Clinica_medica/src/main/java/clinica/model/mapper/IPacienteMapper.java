package clinica.model.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import clinica.model.dto.MedicoDTO;
import clinica.model.dto.PacienteDTO;
import clinica.model.entity.CitaMedica;
import clinica.model.entity.Medico;
import clinica.model.entity.Paciente;

@Mapper(componentModel = "spring")
public interface IPacienteMapper {//debo ignorar el id
    @Mapping(target = "id", ignore = true)
    Paciente dePacienteDTOAPaciente(PacienteDTO pacienteDTO);

    @Mapping(source = "id", target = "id")
    PacienteDTO dePacienteAPacienteDTO(Paciente paciente);
    
    @Mapping(target = "id", ignore = true)
    void actualizarPacienteDesdeDTO(@MappingTarget Paciente paciente, PacienteDTO pacienteDTO);

    List<PacienteDTO> deListaPacienteAListaPacienteDTO(List<Paciente> paciente);

    List<Paciente> deListaPacienteDTOAListaPaciente(List<PacienteDTO> pacienteDTO);


}
