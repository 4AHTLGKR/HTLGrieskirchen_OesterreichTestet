package at.htlgkr.htltestet.data;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RegistrationData {
    public String firstname;
    public String lastname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate birthdate;
    public Gender gender;
    public String street;
    public String streetNumber;
    public int plz;
    public String place;
    public String phoneNumber;
    public String email;
    public int screeningStationId;
    public int testDateTimeId;
}
