package App.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import App.models.AccountData;
import App.models.AccountDataPermission;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	AccountDataService accountDataService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AccountData> accData = accountDataService.getAccountDataByUserName(username);
		
		if(accData.isPresent()) {
			ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			for(AccountDataPermission accPerm : accData.get().getAccountDataPermissions()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(accPerm.getPermission().getTitle()));
			}
			
			return new org.springframework.security.core.userdetails.User(accData.get().getUsername(), accData.get().getPassword(), grantedAuthorities);
		}
		
		return null;
	}
}
