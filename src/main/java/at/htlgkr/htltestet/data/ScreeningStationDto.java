package at.htlgkr.htltestet.data;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ScreeningStationDto {
    private String city;
    private String plz;
    private String street;
    private String name;

    public ScreeningStation toScreeningStation() {
        var screeningStation = new ScreeningStation();
        screeningStation.setName(getName());
        screeningStation.setCity(getCity());
        screeningStation.setStreet(getStreet());
        screeningStation.setPlz(Integer.parseInt(getPlz()));
        return screeningStation;
    }
}
