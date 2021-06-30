package at.htlgkr.htltestet.data;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Registration {
    @JsonFormat(pattern = "yyyy-MM-dd")
    public LocalDate birthdate;
    public int screeningStationId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime testDateTime;
}
