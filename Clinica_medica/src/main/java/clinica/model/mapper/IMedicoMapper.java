package clinica.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import clinica.model.dto.MedicoDTO;
import clinica.model.entity.Medico;
//utilizar un mapper personalisado para que no choque entre IDs
//revisar el mapeo de turnos
//@Mapper(componentModel = "spring", uses = ITurnoMapper.class)
@Mapper(componentModel = "spring", uses = ITurnoMapper.class)
public interface IMedicoMapper {
	@Mapping(source = "turnos", target = "turnos")
	@Mapping(source = "id", target = "id")
	MedicoDTO deMedicoAMedicoDTO(Medico medico);
	@Mapping(source = "turnos", target = "turnos")
	@Mapping(target = "id", ignore = true)
	Medico deMedicoDTOAMedico(MedicoDTO medicoDTO);
	@Mapping(target = "turnos", ignore = true)
	@Mapping(source = "id", target = "id")
	void actualizarDeMedicoAMedicoDTO(@MappingTarget Medico medico, MedicoDTO medicoDTO);

//	@Mapping(target = "turnos", source = "turnos")
//    @Mapping(target = "id", ignore = true)
//	MedicoDTO deMedicoAMedicoDTO(Medico medico);
//	@Mapping(target = "turnos", source = "turnos")
//    @Mapping(target = "id", ignore = true)
//	Medico deMedicoDTOAMedico(MedicoDTO medicoDTO);
//	@Mapping(target = "turnos", ignore = true)
//    @Mapping(target = "id", source = "id")
//	void actualizarMedicoDesdeDTO(@MappingTarget Medico medico, MedicoDTO medicoDTO);
//	
//	List<MedicoDTO> deListaMedicoAListaMedicoDTO(List<Medico> listaMedico);
////	List<Medico> deListaMedicoDTOAListaMedico(List<MedicoDTO> listaMedicoDTO);
}
