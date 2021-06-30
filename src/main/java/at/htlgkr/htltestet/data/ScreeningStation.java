package at.htlgkr.htltestet.data;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "screeningstations")
public class ScreeningStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String city;
    private int plz;
    private String street;
    private String building;
}
