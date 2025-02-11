package clinica.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clinica.model.entity.Turno;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long>{
    List<Turno> findByMedicoId(Long medicoId);
}
