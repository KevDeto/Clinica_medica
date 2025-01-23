package clinica.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import clinica.model.dto.TurnoDTO;
import clinica.model.entity.Turno;

@Mapper(componentModel = "spring")
public interface ITurnoMapper {
	ITurnoMapper INSTANCE = Mappers.getMapper(ITurnoMapper.class);
	Turno deTurnoDTOATurno(TurnoDTO turnoDTO);
	TurnoDTO deTurnoATurnoDTO(Turno turno);
}
