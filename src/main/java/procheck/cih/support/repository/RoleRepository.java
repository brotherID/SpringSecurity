package procheck.cih.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import procheck.cih.support.entities.Authorities;

public interface RoleRepository extends JpaRepository<Authorities, String> {

}
