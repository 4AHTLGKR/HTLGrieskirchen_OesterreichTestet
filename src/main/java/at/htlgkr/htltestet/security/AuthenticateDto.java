package at.htlgkr.htltestet.security;

import lombok.Data;

@Data
public class AuthenticateDto {
    private String email;
    private String password;
}
