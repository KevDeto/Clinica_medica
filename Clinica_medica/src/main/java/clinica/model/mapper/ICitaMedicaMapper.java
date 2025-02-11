package clinica.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import clinica.model.dto.CitaMedicaDTO;
import clinica.model.entity.CitaMedica;

@Mapper(componentModel = "spring")
public interface ICitaMedicaMapper {
    @Mapping(source = "pacienteId", target = "paciente.codigoPersona")
    @Mapping(source = "medicoId", target = "medico.codigoPersona")
    @Mapping(source = "turnoId", target = "turno.codigoTurno")
    @Mapping(source = "servicioId", target = "servicio.codigoServicio")
    @Mapping(source = "paqueteId", target = "paquete.codigoPaquete")
    @Mapping(target = "codigoCitaMedica", ignore = true)
    CitaMedica deCitaMedicaDTOACitaMedica(CitaMedicaDTO citaMedicaDTO);

    @Mapping(source = "paciente.codigoPersona", target = "pacienteId")
    @Mapping(source = "medico.codigoPersona", target = "medicoId")
    @Mapping(source = "turno.codigoTurno", target = "turnoId")
    @Mapping(source = "servicio.codigoServicio", target = "servicioId")
    @Mapping(source = "paquete.codigoPaquete", target = "paqueteId")
    @Mapping(source = "codigoCitaMedica", target = "codigoCitaMedica")
    CitaMedicaDTO deCitaMedicaACitaMedicaDTO(CitaMedica citaMedica);

    @Mapping(target = "paciente.codigoPersona", ignore = true)
    @Mapping(target = "medico.codigoPersona", ignore = true)
	@Mapping(target = "turno.codigoTurno", ignore = true)
    @Mapping(target = "servicio.codigoServicio", ignore = true)
    @Mapping(target = "paquete.codigoPaquete", ignore = true)
    @Mapping(target = "codigoCitaMedica", ignore = true)
    void actualizarDeCitaMedicaACitaMedicaDTO(@MappingTarget CitaMedica citaMedica, CitaMedicaDTO citaMedicaDTO); 
}
