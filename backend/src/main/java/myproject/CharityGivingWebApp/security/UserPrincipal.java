package myproject.CharityGivingWebApp.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrincipal implements UserDetails {

	private final Long userId;
	private final String email;
	@JsonIgnore
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;
	
    public UserPrincipal(Long userId, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipleBuilder builder() {
        return new UserPrincipleBuilder();
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
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
	
	public Long getUserId() {
		return userId;
	}
	
	public static class UserPrincipleBuilder {
        private Long userId;
        private String email;
        private String password;
        private Collection<? extends GrantedAuthority> authorities;

        public UserPrincipleBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserPrincipleBuilder email(String email) {
            this.email = email;
            return this;
        }
        
        public UserPrincipleBuilder password(String password) {
            this.password = password;
            return this;
        }


        public UserPrincipleBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public UserPrincipal build() {
            return new UserPrincipal(userId, email, password, authorities);
        }
    }
	
}
