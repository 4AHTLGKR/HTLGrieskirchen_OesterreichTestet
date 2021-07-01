package at.htlgkr.htltestet.security;

import at.htlgkr.htltestet.data.Employee;
import at.htlgkr.htltestet.data.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    public JwtUserDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usrName) throws UsernameNotFoundException {
        Optional<Employee> u = employeeRepository.findById(usrName.toLowerCase());
        if (u.isEmpty())
            throw new UsernameNotFoundException("User " + usrName + " not found");
        return new User(usrName.toLowerCase(), u.get().getPassword(), new ArrayList<>());
    }
}
