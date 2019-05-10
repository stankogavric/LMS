package App.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
}
