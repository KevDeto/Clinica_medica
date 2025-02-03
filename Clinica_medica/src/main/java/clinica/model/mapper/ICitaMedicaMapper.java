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
    @Mapping(source = "servicioId", target = "servicio.codigoServicio")
    @Mapping(source = "paqueteId", target = "paquete.codigoPaquete")
    @Mapping(target = "id", ignore = true)
    CitaMedica deCitaMedicaDTOACitaMedica(CitaMedicaDTO citaMedicaDTO);

    @Mapping(source = "paciente.id", target = "pacienteId")
    @Mapping(source = "medico.id", target = "medicoId")
    @Mapping(source = "turno.id", target = "turnoId")
    @Mapping(source = "servicio.codigoServicio", target = "servicioId")
    @Mapping(source = "paquete.codigoPaquete", target = "paqueteId")
    @Mapping(source = "id", target = "id")
    CitaMedicaDTO deCitaMedicaACitaMedicaDTO(CitaMedica citaMedica);

    @Mapping(target = "paciente.id", ignore = true)
    @Mapping(target = "medico.id", ignore = true)
	@Mapping(target = "turno.id", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "servicio.codigoServicio", ignore = true)
    @Mapping(target = "paquete.codigoPaquete", ignore = true)
    void actualizarDeCitaMedicaACitaMedicaDTO(@MappingTarget CitaMedica citaMedica, CitaMedicaDTO citaMedicaDTO); 
}
