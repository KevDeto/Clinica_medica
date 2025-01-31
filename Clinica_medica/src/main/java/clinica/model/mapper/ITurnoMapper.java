package clinica.model.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import clinica.model.dto.TurnoDTO;
import clinica.model.entity.CitaMedica;
import clinica.model.entity.Turno;

@Mapper(componentModel = "spring")
public interface ITurnoMapper {//al actualizar debo ignorar el id de medico en el DTO
    @Mapping(source = "medicoId", target = "medico.id")
    @Mapping(target = "citasMedicas", ignore = true)
    @Mapping(target = "dia", source = "diaTurno")
    @Mapping(target = "id", ignore = true)
    Turno deTurnoDTOATurno(TurnoDTO turnoDTO);

    @Mapping(source = "medico.id", target = "medicoId")
    @Mapping(source = "citasMedicas", target = "citasMedicas", qualifiedByName = "mapCitasMedicasToIds")
    @Mapping(source = "dia", target = "diaTurno")
    @Mapping(source = "id", target = "id")
    TurnoDTO deTurnoATurnoDTO(Turno turno);

    @Mapping(source = "medicoId", target = "medico.id")
    @Mapping(target = "citasMedicas", ignore = true)
    @Mapping(source = "diaTurno", target = "dia")
    @Mapping(target = "id", ignore = true)
    void actualizarDeTurnoATurnoDTO(@MappingTarget Turno turno, TurnoDTO turnoDTO);

    @Named("mapCitasMedicasToIds")
    default List<Long> mapCitasMedicasToIds(List<CitaMedica> citasMedicas) {
        if (citasMedicas == null) {
            return Collections.emptyList();
        }
        return citasMedicas.stream().map(CitaMedica::getId).collect(Collectors.toList());
    }
}