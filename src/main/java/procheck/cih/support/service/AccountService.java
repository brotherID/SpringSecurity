package procheck.cih.support.service;

import procheck.cih.support.entities.Authorities;
import procheck.cih.support.entities.User;

public interface AccountService {

	User addNewUser(String username, String password, String confirmPassword);

	Authorities addNewRole(String role);

	void addRoleToUser(String username, String role);

	void removeRoleFormUser(String username, String role);

	User loadUserByUsername(String username);

}
