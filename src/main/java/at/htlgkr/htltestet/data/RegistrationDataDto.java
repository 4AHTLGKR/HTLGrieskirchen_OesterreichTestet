package at.htlgkr.htltestet.data;

import at.htlgkr.htltestet.pdf.TestResult;
import lombok.Data;

@Data
public class RegistrationDataDto {

    private int id;

    private String firstname;
    private String lastname;

    private boolean isTested;
    private TestResult testResult;

    public boolean getIsTested() {
        return isTested;
    }

    public void setIsTested(boolean tested) {
        isTested = tested;
    }

    public static RegistrationDataDto RegistrationToDto(RegistrationData r){
        RegistrationDataDto rdto = new RegistrationDataDto();
        rdto.setFirstname(r.getFirstname());
        rdto.setId(r.getId());
        rdto.setLastname(r.getLastname());
        return rdto;
    }

}
