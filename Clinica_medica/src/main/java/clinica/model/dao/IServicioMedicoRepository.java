package clinica.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clinica.model.entity.ServicioMedico;

@Repository
public interface IServicioMedicoRepository extends JpaRepository<ServicioMedico, Long>{

}
