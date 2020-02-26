package br.com.salao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.salao.entity.User;
import br.com.salao.repository.UserRepository;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

	private UserRepository userRepo;
	
	@Autowired
	public UserRepositoryUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if( user != null ) {
			return user;
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}

}
