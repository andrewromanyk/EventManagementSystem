package ua.edu.ukma.event_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ua.edu.ukma.event_management_system.service.interfaces.UserService;

import java.util.Map;

@RestController
public class AuthController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("login")
	public String login(@RequestBody Map<String, String> map) {
		String username = map.get("username");
		String password = map.get("password");
		if (username == null || password == null) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(401));
		}
		return userService.verify(username, password);
	}
}
