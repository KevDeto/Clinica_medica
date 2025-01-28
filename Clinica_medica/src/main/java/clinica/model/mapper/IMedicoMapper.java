package clinica.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import clinica.model.dto.MedicoDTO;
import clinica.model.entity.Medico;

@Mapper(componentModel = "spring", uses = ITurnoMapper.class)
public interface IMedicoMapper {
	@Mapping(target = "turnos", ignore = true)
	@Mapping(target = "id", ignore = true)
	Medico deMedicoDTOAMedico(MedicoDTO medicoDTO);
	@Mapping(source = "turnos", target = "turnos")
	@Mapping(source = "id", target = "id")
	MedicoDTO deMedicoAMedicoDTO(Medico medico);
	@Mapping(target = "turnos", ignore = true)
	@Mapping(target = "id", ignore = true)
	void actualizarDeMedicoAMedicoDTO(@MappingTarget Medico medico, MedicoDTO medicoDTO);
}
