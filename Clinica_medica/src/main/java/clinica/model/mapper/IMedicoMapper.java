package clinica.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import clinica.model.dto.MedicoDTO;
import clinica.model.entity.Medico;
//utilizar un mapper personalisado para que no choque entre IDs
@Mapper(componentModel = "spring")
public interface IMedicoMapper {
	IMedicoMapper INSTANCE = Mappers.getMapper(IMedicoMapper.class);
	MedicoDTO deMedicoAMedicoDTO(Medico medico);
	Medico deMedicoDTOAMedico(MedicoDTO medicoDTO);
	List<MedicoDTO> deListaMedicoAListaMedicoDTO(List<Medico> listaMedico);
	List<Medico> deListaMedicoDTOAListaMedico(List<MedicoDTO> listaMedicoDTO);
}
