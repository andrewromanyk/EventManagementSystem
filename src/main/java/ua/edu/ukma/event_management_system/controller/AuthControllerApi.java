package ua.edu.ukma.event_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.edu.ukma.event_management_system.service.interfaces.UserService;

@RestController
public class AuthControllerApi {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("login")
	public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
		if (username == null || password == null) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(401));
		}

		String token = userService.verify(username, password);

		ResponseCookie cookie = ResponseCookie.from("jwtToken", token)
				.httpOnly(true)
				.secure(true)
				.path("/")
				.maxAge(24 * 60 * (long) 60)
				.sameSite("Strict")
				.build();

		return ResponseEntity
				.status(HttpStatus.FOUND)
				.header(HttpHeaders.SET_COOKIE, cookie.toString())
				.header(HttpHeaders.LOCATION, "/myprofile")
				.body("Login successful");
	}

	@GetMapping("/tologout")
	public ResponseEntity<String> logout() {
		ResponseCookie cookie = ResponseCookie.from("jwtToken")
				.httpOnly(true)
				.secure(true)
				.path("/")
				.maxAge(0)
				.sameSite("Strict")
				.build();

		return ResponseEntity
				.status(HttpStatus.FOUND)
				.header(HttpHeaders.SET_COOKIE, cookie.toString())
				.header(HttpHeaders.LOCATION, "/FAQ.html")
				.body("Logout successful");
	}

}
