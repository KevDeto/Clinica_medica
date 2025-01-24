package clinica.model.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import clinica.exceptions.ApplicationException;
import clinica.exceptions.ErrorCode;
import clinica.model.dao.IMedicoRepository;
import clinica.model.dto.TurnoDTO;
import clinica.model.entity.Medico;
import clinica.model.entity.Turno;

@Mapper(componentModel = "spring", uses = IMedicoRepository.class)
public interface ITurnoMapper {
//	ITurnoMapper INSTANCE = Mappers.getMapper(ITurnoMapper.class);
    @Mapping(source = "medico_id", target = "medico", qualifiedByName = "mapearMedicoIdAMedico")
    Turno deTurnoDTOATurno(TurnoDTO turnoDTO, @Context IMedicoRepository medicoRepository);

    @Mapping(source = "medico.id", target = "medico_id")
    TurnoDTO deTurnoATurnoDTO(Turno turno);

    @Named("mapearMedicoIdAMedico")
    default Medico mapearMedicoIdAMedico(Long medico_id, @Context IMedicoRepository medicoRepository) {
        if (medico_id == null) {
            return null;
        }
        return medicoRepository.findById(medico_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,
                		"El medico con ID " + medico_id + " no existe.", null));
    }
}



