package com.ecobank.security.model;

import com.ecobank.auth.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Usuario usuario) {
        this.id = usuario.getUsuarioId();
        this.email = usuario.getUsuarioEmail();
        this.password = usuario.getUsuarioPassword();
        this.authorities = getAuthoritiesFromRoles(usuario);
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesFromRoles(Usuario usuario) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        usuario.getRoles().forEach(rol -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getRolNombre().toUpperCase()));
            rol.getPermisos().forEach(permiso -> {
                authorities.add(new SimpleGrantedAuthority(permiso.getPermisoNombre()));
            });
        });
        return authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
}
