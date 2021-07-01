package at.htlgkr.htltestet.sec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserDtoRepository userDtoRepository;

    public SecurityUserDetailsService(UserDtoRepository userDtoRepository) {
        this.userDtoRepository = userDtoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userDtoRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));
        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        return u;
    }
}
