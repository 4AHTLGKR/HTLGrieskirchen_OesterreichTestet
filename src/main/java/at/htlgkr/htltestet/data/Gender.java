package at.htlgkr.htltestet.data;


public enum Gender {
    MANNLICH ("männlich"), WEIBLICH ("weiblich"), DIVERS("divers");
    private final String gender;

    Gender(String gender){
        this.gender = gender;
    }

    public String getGender()
    {
        return gender;
    }
}
