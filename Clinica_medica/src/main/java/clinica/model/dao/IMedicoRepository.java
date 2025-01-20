package clinica.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clinica.model.entity.Medico;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico, Long>{
}
