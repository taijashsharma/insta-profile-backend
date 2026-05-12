package instaProfilecom.example.instaProfile.security;

import instaProfilecom.example.instaProfile.entity.User;
import instaProfilecom.example.instaProfile.repository.UserRepository;
import instaProfilecom.example.instaProfile.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepo;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String auth = request.getHeader("Authorization");

        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring(7);

            if (jwtService.verifyToken(token)) {
                String username = jwtService.extractUsername(token);

                User user = userRepo.findByUsername(username).orElse(null);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user, null, List.of()
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}

