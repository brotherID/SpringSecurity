package procheck.cih.support.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import procheck.cih.support.entities.Authorities;
import procheck.cih.support.entities.User;
import procheck.cih.support.repository.RoleRepository;
import procheck.cih.support.repository.UserRepository;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	@Override
	public User addNewUser(String username, String password, String confirmPassword) {
		User user = userRepository.findByUsername(username);
		if (user != null)
			throw new RuntimeException("this user already exist");
		if (!password.equals(confirmPassword))
			throw new RuntimeException("Passwords not match");
		user = User.builder().username(username).password(passwordEncoder.encode(password)).enabled(true).build();
		return userRepository.save(user);
	}

	@Override
	public Authorities addNewRole(String role) {
		Authorities appRole =   roleRepository.findById(role).orElse(null);
		if(appRole != null) throw  new RuntimeException("this role already exist");
		appRole =Authorities.builder().authority(role).build();
		return roleRepository.save(appRole);
	}

	@Override
	public void addRoleToUser(String username, String role) {
        User user = userRepository.findByUsername(username);
        Authorities authorities =  roleRepository.findById(role).get();
        user.getRoles().add(authorities);
	}

	@Override
	public void removeRoleFormUser(String username, String role) {
		User user = userRepository.findByUsername(username);
        Authorities authorities =  roleRepository.findById(role).get();
        user.getRoles().remove(authorities);
	}

	@Override
	public User loadUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
