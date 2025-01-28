package clinica.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import clinica.model.dto.TurnoDTO;
import clinica.model.entity.Medico;
import clinica.model.entity.Turno;

@Mapper(componentModel = "spring")
public interface ITurnoMapper {
	@Mapping(source = "medico_id", target = "medico", qualifiedByName = "mapMedicoIdToMedico")
	@Mapping(target = "id", ignore = true)
	Turno deTurnoDTOATurno(TurnoDTO turnoDTO);
	@Mapping(source = "medico", target = "medico_id", qualifiedByName = "mapMedicoToMedicoId")
	@Mapping(source = "id", target = "id")
	TurnoDTO deTurnoATurnoDTO(Turno turno);
	@Mapping(source = "medico_id", target = "medico.id")
	@Mapping(target = "id", ignore = true)
	void actualizarDeTurnoATurnoDTO(@MappingTarget Turno turno, TurnoDTO turnoDTO);

    @Named("mapMedicoIdToMedico")
    default Medico mapMedicoIdToMedico(Long medico_id) {
//        if (medico_id == null) {
//            return null;
//        }
        Medico medico = new Medico();
        medico.setId(medico_id);
        return medico;
    }

    @Named("mapMedicoToMedicoId")
    default Long mapMedicoToMedicoId(Medico medico) {
//        if (medico == null) {
//            return null;
//        }
        return medico.getId();
    }
}