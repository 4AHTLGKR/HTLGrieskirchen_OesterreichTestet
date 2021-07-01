package at.htlgkr.htltestet.sec;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private String username;
    private String password;
    private int screeningStationId;

    public User() {}
    public User(String username, String password, int screeningStationId) {
        this.username = username;
        this.password = password;
        this.screeningStationId = screeningStationId;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScreeningStationId() {
        return screeningStationId;
    }

    public void setScreeningStationId(int screeningStationId) {
        this.screeningStationId = screeningStationId;
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
