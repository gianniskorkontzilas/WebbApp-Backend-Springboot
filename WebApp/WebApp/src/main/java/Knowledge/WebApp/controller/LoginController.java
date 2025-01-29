package Knowledge.WebApp.controller;

import Knowledge.WebApp.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials credentials) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getLogin(), credentials.getPassword())
        );

        String jwt = jwtUtil.generateToken(credentials.getLogin());
        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        return ResponseEntity.ok(jwt);
    }

//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody UserCredentials credentials) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(credentials.getLogin(), credentials.getPassword())
//        );
//
//        String jwt = jwtUtil.generateToken(credentials.getLogin());
//        Map<String, String> response = new HashMap<>();
//        response.put("token", jwt);
//        return ResponseEntity.ok(response);
//    }


    public static class UserCredentials {
        private String login;
        private String password;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
