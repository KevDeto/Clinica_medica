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

import clinica.model.dto.TurnoDTO;
import clinica.model.entity.CitaMedica;
import clinica.model.entity.Turno;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITurnoMapper {
    @Mapping(source = "medicoId", target = "medico.id")
    @Mapping(target = "diaTurno", source = "diaTurno")
    @Mapping(target = "citasMedicas", ignore = true)
    @Mapping(target = "codigoTurno", ignore = true)
    Turno deTurnoDTOATurno(TurnoDTO turnoDTO);

    @Mapping(source = "citasMedicas", target = "citasMedicas", qualifiedByName = "mapCitasMedicasToIds")
    @Mapping(source = "medico.id", target = "medicoId")
    @Mapping(source = "diaTurno", target = "diaTurno")
    @Mapping(source = "codigoTurno", target = "codigoTurno")
    TurnoDTO deTurnoATurnoDTO(Turno turno);

    @Mapping(source = "medicoId", target = "medico.id")
    @Mapping(source = "diaTurno", target = "diaTurno")
    @Mapping(target = "citasMedicas", ignore = true)
    @Mapping(target = "codigoTurno", ignore = true)
    void actualizarDeTurnoATurnoDTO(@MappingTarget Turno turno, TurnoDTO turnoDTO);

    @Named("mapCitasMedicasToIds")
    default List<Long> mapCitasMedicasToIds(List<CitaMedica> citasMedicas) {
        if (citasMedicas == null) {
            return Collections.emptyList();
        }
        return citasMedicas.stream().map(CitaMedica::getCodigoCitaMedica).collect(Collectors.toList());
    }
}