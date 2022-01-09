package zpzgzerg.hhyboard.security.config;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import zpzgzerg.hhyboard.entity.Member;

import java.util.Collection;

@Getter
public class CustomUser implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;
    private String username;
    private String password;
    private Member member;

    public CustomUser(Collection<? extends GrantedAuthority> authorities, String username, String password, Member member) {
        this.authorities = authorities;
        this.username = username;
        this.password = password;
        this.member = member;
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
