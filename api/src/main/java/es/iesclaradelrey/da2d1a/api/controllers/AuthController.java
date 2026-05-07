package es.iesclaradelrey.da2d1a.api.controllers;

import es.iesclaradelrey.da2d1a.api.dto.LoginRequestDTO;
import es.iesclaradelrey.da2d1a.api.dto.RefreshRequestDTO;
import es.iesclaradelrey.da2d1a.api.dto.TokenResponseDTO;
import es.iesclaradelrey.da2d1a.api.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtService jwtService;
    @Autowired private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO dto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails user = userDetailsService.loadUserByUsername(dto.getUsername());
        String accessToken  = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return ResponseEntity.ok(new TokenResponseDTO(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@Valid @RequestBody RefreshRequestDTO dto) {
        try {
            String username = jwtService.extractUsername(dto.getRefreshToken());
            UserDetails user = userDetailsService.loadUserByUsername(username);

            if (!jwtService.isTokenValid(dto.getRefreshToken(), user)) {
                return ResponseEntity.badRequest().build();
            }

            String newAccessToken = jwtService.generateAccessToken(user);
            return ResponseEntity.ok(new TokenResponseDTO(newAccessToken, dto.getRefreshToken()));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
