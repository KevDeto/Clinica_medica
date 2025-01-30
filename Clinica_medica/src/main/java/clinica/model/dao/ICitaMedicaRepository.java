package clinica.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clinica.model.entity.CitaMedica;

@Repository
public interface ICitaMedicaRepository extends JpaRepository<CitaMedica, Long>{
}
