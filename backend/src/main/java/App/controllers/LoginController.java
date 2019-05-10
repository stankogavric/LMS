package App.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import App.models.AccountData;
import App.services.LoginService;

@CrossOrigin(origins = { "http://localhost:4200" })
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	LoginService ls;
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, String>> login(@RequestBody AccountData accData) {		
		return ls.login(accData);
	}

	@RequestMapping("/test")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>("Test success!", HttpStatus.OK);
	}
	
	@RequestMapping("/testStd")
	@Secured("ROLE_STUDENT")
	public ResponseEntity<String> testStd() {
		return new ResponseEntity<String>("Test std success!", HttpStatus.OK);
	}
	
}
