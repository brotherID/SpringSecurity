package procheck.cih.support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import procheck.cih.support.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

}
