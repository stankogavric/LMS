package App.services;

import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import App.models.AccountData;
import App.models.AccountDataPermission;
import App.repositories.AccountDataRepository;
import App.repositories.PermissionRepository;
import App.utils.TokenUtils;

@Service
public class LoginService {
	@Autowired
	AccountDataService accS;

	@Autowired
	AccountDataRepository accR;

	@Autowired
	PermissionRepository pr;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	public ResponseEntity<HashMap<String, String>> login(AccountData accData) {
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(accData.getUsername(),
					accData.getPassword());
			
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails details = userDetailsService.loadUserByUsername(accData.getUsername());
			String userToken = tokenUtils.generateToken(details);
			
			HashMap<String, String> data = new HashMap<String, String>();
			data.put("token", userToken);
			
			return new ResponseEntity<HashMap<String, String>>(data, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<HashMap<String, String>>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	public void addPermsion(AccountData accData, String role) {
		accData = accR.save(accData);
		accData.setAccountDataPermissions(new HashSet<AccountDataPermission>());
		accData.getAccountDataPermissions().add(new AccountDataPermission(accData, pr.getByTitle(role).get()));
		accR.save(accData);
	}
	
}
