package clinica.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clinica.model.entity.PaqueteServicio;

@Repository
public interface IPaqueteMedicoRepository extends JpaRepository<PaqueteServicio, Long>{

}
