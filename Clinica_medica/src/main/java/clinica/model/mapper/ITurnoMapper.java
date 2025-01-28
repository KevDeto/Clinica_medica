package clinica.model.mapper;

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import clinica.exceptions.ApplicationException;
import clinica.exceptions.ErrorCode;
import clinica.model.dao.IMedicoRepository;
import clinica.model.dto.TurnoDTO;
import clinica.model.entity.Medico;
import clinica.model.entity.Turno;

//@Mapper(componentModel = "spring", uses = IMedicoRepository.class)
@Mapper(componentModel = "spring")
public interface ITurnoMapper {
	@Mapping(source = "medico_id", target = "medico", qualifiedByName = "mapMedicoIdToMedico")
	@Mapping(target = "id", ignore = true)
	Turno deTurnoDTOATurno(TurnoDTO turnoDTO);
	@Mapping(source = "medico", target = "medico_id", qualifiedByName = "mapMedicoToMedicoId")
	@Mapping(target = "id", ignore = true)
	TurnoDTO deTurnoATurnoDTO(Turno turno);
	@Mapping(source = "medico_id", target = "medico.id")
	@Mapping(target = "id", source = "id")
	void actualizarDeTurnoATurnoDTO(@MappingTarget Turno turno, TurnoDTO turnoDTO);

    @Named("mapMedicoIdToMedico")
    default Medico mapMedicoIdToMedico(Long medico_id) {
        if (medico_id == null) {
            return null;
        }
        Medico medico = new Medico();
        medico.setId(medico_id);
        return medico;
    }

    @Named("mapMedicoToMedicoId")
    default Long mapMedicoToMedicoId(Medico medico) {
        if (medico == null) {
            return null;
        }
        return medico.getId();
    }
//    @Mapping(source = "medico_id", target = "medico", qualifiedByName = "mapearMedicoIdAMedico")
//    Turno deTurnoDTOATurno(TurnoDTO turnoDisponibleDTO, @Context IMedicoRepository medicoRepository);
//
//    @Mapping(source = "medico.id", target = "medico_id")
//    TurnoDTO deTurnoATurnoDTO(Turno turno);
//
//    @Mapping(source = "medico_id", target = "medico.id")
//    @Mapping(source = "id", target = "id")
//    void actualizarTurnoDesdeDTO(@MappingTarget Turno turno, TurnoDTO turnoDTO);
//
//    List<TurnoDTO> deListaTurnoAListaTurnoDTO(List<Turno> listaTurno);
//
//    List<Turno> deListaTurnoDTOAListaTurno(List<TurnoDTO> listaTurnoDTO, @Context IMedicoRepository medicoRepository);
//
//    @Named("mapearMedicoIdAMedico")
//    default Medico mapearMedicoIdAMedico(Long medico_id, @Context IMedicoRepository medicoRepository) {
//        return medicoRepository.getReferenceById(medico_id);
//    }
}