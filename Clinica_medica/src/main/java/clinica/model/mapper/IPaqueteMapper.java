package clinica.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import clinica.model.dto.PaqueteServicioDTO;
import clinica.model.entity.PaqueteServicio;
import clinica.model.entity.ServicioMedico;

@Mapper(componentModel = "spring")
public interface IPaqueteMapper {
    @Mapping(target = "listaServicios", source = "listaServicios", qualifiedByName = "mapServiciosToIds")
    @Mapping(target = "codigoPaquete", ignore = true)
    PaqueteServicioDTO dePaqueteServicioAPaqueteServicioDTO(PaqueteServicio paqueteServicio);

    @Mapping(target = "listaServicios", ignore = true)
    PaqueteServicio dePaqueteServicioDTOAPaqueteServicio(PaqueteServicioDTO paqueteServicioDTO);

    @Mapping(target = "listaServicios", ignore = true)
    @Mapping(target = "codigoPaquete", ignore = true)
    void actualizarPaqueteServicioDesdeDTO(@MappingTarget PaqueteServicio paqueteServicio, PaqueteServicioDTO paqueteServicioDTO);
    
    @Named("mapServiciosToIds")
    default List<Long> mapServiciosToIds(List<ServicioMedico> servicios) {
        if (servicios == null) {
            return Collections.emptyList();
        }
        return servicios.stream()
                .map(ServicioMedico::getCodigoServicio)
                .collect(Collectors.toList());
    }
}
