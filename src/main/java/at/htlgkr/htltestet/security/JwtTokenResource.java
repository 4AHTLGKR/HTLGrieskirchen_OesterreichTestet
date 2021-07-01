package at.htlgkr.htltestet.security;

public class JwtTokenResource {
    private final String jwttoken;

    public JwtTokenResource(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return jwttoken;
    }
}
