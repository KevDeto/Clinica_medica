package clinica.model.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import clinica.model.dto.ServicioMedicoDTO;
import clinica.model.entity.ServicioMedico;

@Mapper(componentModel = "spring")
public interface IServicioMapper {

//    @Mapping(target = "listaPaquetes", ignore = true) 
//    @Mapping(target = "citaMedica", ignore = true)  
    ServicioMedicoDTO deServicioMedicoAServicioMedicoDTO(ServicioMedico servicioMedico);

    @Mapping(target = "listaPaquetes", ignore = true) 
    @Mapping(target = "citaMedica", ignore = true)  
    ServicioMedico deServicioMedicoDTOAServicioMedico(ServicioMedicoDTO servicioMedicoDTO);

    @Mapping(target = "listaPaquetes", ignore = true) 
    @Mapping(target = "citaMedica", ignore = true)   
    void actualizarServicioMedicoDesdeDTO(@MappingTarget ServicioMedico servicioMedico, ServicioMedicoDTO servicioMedicoDTO);}