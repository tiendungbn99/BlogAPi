package com.ltw.blog.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ltw.blog.entities.User;

public class UserPrincipal implements UserDetails{
	private static final long serialVersionUID = 1L;

    private Integer id;

    private String userName;

    private String password;

    private Collection<? extends GrantedAuthority> roles;

    public UserPrincipal(Integer id, String userName, String password, 
    		Collection<? extends GrantedAuthority> roles) {
	
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}

	public static UserPrincipal build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getPassword(),
                user.getUsername(),
                authorities
        );
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrincipal user = (UserPrincipal) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
