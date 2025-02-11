package clinica.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clinica.security.entity.RolEntity;


@Repository
public interface IRoleRepository extends JpaRepository<RolEntity, Long>{
}
