package clinica.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import clinica.model.dto.PacienteDTO;
import clinica.model.entity.Paciente;

@Mapper(componentModel = "spring")
public interface IPacienteMapper {
	@Mapping(target = "citasMedicas", ignore = true)
    @Mapping(target = "id", ignore = true)
    Paciente dePacienteDTOAPaciente(PacienteDTO pacienteDTO);

    @Mapping(source = "id", target = "codigoPaciente")
    PacienteDTO dePacienteAPacienteDTO(Paciente paciente);
    
    @Mapping(target = "id", ignore = true)
    void actualizarPacienteDesdeDTO(@MappingTarget Paciente paciente, PacienteDTO pacienteDTO);
}
