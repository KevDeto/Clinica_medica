package clinica.model.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import clinica.model.dto.MedicoDTO;
import clinica.model.entity.Medico;
import clinica.model.entity.Turno;

@Mapper(componentModel = "spring")
public interface IMedicoMapper {
    @Mapping(target = "turnos", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "citasMedicas", ignore = true)
    Medico deMedicoDTOAMedico(MedicoDTO medicoDTO);

    @Mapping(source = "turnos", target = "turnos", qualifiedByName = "mapTurnosToIds")
    @Mapping(source = "id", target = "codigoMedico")
    MedicoDTO deMedicoAMedicoDTO(Medico medico);

    @Mapping(target = "turnos", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "citasMedicas", ignore = true)
    void actualizarDeMedicoAMedicoDTO(@MappingTarget Medico medico, MedicoDTO medicoDTO);

    @Named("mapTurnosToIds")
    default List<Long> mapTurnosToIds(List<Turno> turnos) {
        if (turnos == null) {
            return Collections.emptyList();
        }
        return turnos.stream().map(Turno::getCodigoTurno).collect(Collectors.toList());
    }
}
