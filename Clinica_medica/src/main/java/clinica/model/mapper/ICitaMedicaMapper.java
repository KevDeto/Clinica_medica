package clinica.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import clinica.model.dto.CitaMedicaDTO;
import clinica.model.entity.CitaMedica;
import clinica.model.entity.Medico;
import clinica.model.entity.Paciente;
import clinica.model.entity.Turno;

@Mapper(componentModel = "spring")
public interface ICitaMedicaMapper {
    @Mapping(source = "pacienteId", target = "paciente.id")
    @Mapping(source = "medicoId", target = "medico.id")
    @Mapping(source = "turnoId", target = "turno.id")
    @Mapping(target = "id", ignore = true)
    CitaMedica deCitaMedicaDTOACitaMedica(CitaMedicaDTO citaMedicaDTO);

    @Mapping(source = "paciente.id", target = "pacienteId")
    @Mapping(source = "medico.id", target = "medicoId")
    @Mapping(source = "turno.id", target = "turnoId")
    @Mapping(source = "id", target = "id")
    CitaMedicaDTO deCitaMedicaACitaMedicaDTO(CitaMedica citaMedica);

    @Mapping(source = "pacienteId", target = "paciente.id")
    @Mapping(source = "medicoId", target = "medico.id")
    @Mapping(source = "turnoId", target = "turno.id")
    @Mapping(target = "id", ignore = true)
    void actualizarDeCitaMedicaACitaMedicaDTO(@MappingTarget CitaMedica citaMedica, CitaMedicaDTO citaMedicaDTO);
    
}
