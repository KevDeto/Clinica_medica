package clinica.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clinica.model.entity.Paciente;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long>{
}
