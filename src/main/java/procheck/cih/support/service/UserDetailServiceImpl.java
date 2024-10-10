package procheck.cih.support.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import procheck.cih.support.entities.User;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = accountService.loadUserByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(String.format("User % not found ", username));

		String[] roles = user.getRoles().stream().map(u -> u.getAuthority()).toArray(String[]::new);

		UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPassword()).roles(roles).build();

		return userDetails;
	}

}
