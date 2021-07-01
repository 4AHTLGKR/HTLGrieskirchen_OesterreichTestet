package at.htlgkr.htltestet.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class Employee {
    @Id
    private String email;
    private String password;
}
