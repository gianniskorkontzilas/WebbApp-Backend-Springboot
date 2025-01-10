package Knowledge.WebApp.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;
    private final String credentials;

    public JwtAuthenticationToken(Object principal, String credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

        @Override
        public Object getPrincipal () {
            return principal;
        }

    }


