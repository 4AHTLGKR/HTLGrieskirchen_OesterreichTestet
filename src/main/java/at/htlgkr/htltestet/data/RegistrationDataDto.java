package at.htlgkr.htltestet.data;

import at.htlgkr.htltestet.pdf.TestResult;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RegistrationDataDto {

    private List<RegistrationData> regs;

    public RegistrationDataDto(List<RegistrationData> regs) {
        this.regs = regs;
    }

    public RegistrationDataDto() {
        this.regs = new ArrayList<>();
    }

    public void addRegs(RegistrationData registrationData){
        this.regs.add(registrationData);
    }

    public List<RegistrationData> getRegs() {
        return regs;
    }

    public void setRegs(List<RegistrationData> regs) {
        this.regs = regs;
    }
}
