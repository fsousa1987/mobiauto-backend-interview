package com.francisco.backend.mobiauto.domain.model;

import com.francisco.backend.mobiauto.api.enums.UsuarioPerfil;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
@Entity
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false, name = "nome")
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false, name = "senha")
    private String password;

    @Enumerated(EnumType.STRING)
    private UsuarioPerfil usuarioPerfil;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthoritiesForPerfil(usuarioPerfil);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesForPerfil(UsuarioPerfil perfil) {
        return switch (perfil) {
            case ADMIN -> Stream.of("ROLE_ADMIN", "ROLE_PROPRIETARIO")
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            case PROPRIETARIO -> List.of(new SimpleGrantedAuthority("ROLE_PROPRIETARIO"));
            case GERENTE -> List.of(new SimpleGrantedAuthority("ROLE_GERENTE"));
            default -> List.of(new SimpleGrantedAuthority("ROLE_ASSISTENTE"));
        };
    }

}
