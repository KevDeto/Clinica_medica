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

@Mapper(componentModel = "spring", uses = IMedicoRepository.class)
public interface ITurnoMapper {

	@Mapping(source = "medico_id", target = "medico", qualifiedByName = "mapearMedicoIdAMedico")
    Turno deTurnoDTOATurno(TurnoDTO turnoDisponibleDTO, @Context IMedicoRepository medicoRepository);

    @Mapping(source = "medico.id", target = "medico_id")
    TurnoDTO deTurnoATurnoDTO(Turno turno);


    @Mapping(source = "medico_id", target = "medico.id")
    @Mapping(target = "id", ignore = true)
	void actualizarTurnoDesdeDTO(@MappingTarget Turno turno, TurnoDTO turnoDTO);
    
	List<TurnoDTO> deListaTurnoAListaTurnoDTO(List<Turno> listaTurno);
	List<Turno> deListaTurnoDTOAListaTurno(List<TurnoDTO> listaTurnoDTO);
	
    @Named("mapearMedicoIdAMedico")
    default Medico mapearMedicoIdAMedico(Long medico_id, @Context IMedicoRepository medicoRepository) {
        return medicoRepository.findById(medico_id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND,
                		"El medico con ID " + medico_id + " no existe.", null));
    }
}