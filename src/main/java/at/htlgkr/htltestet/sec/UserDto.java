package at.htlgkr.htltestet.sec;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class UserDto extends User {
    @Id
    private String username;
    private String password;
}
