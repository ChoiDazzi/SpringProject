package kr.or.ddit.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
@Slf4j
public class CustomNoOpPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        log.warn("before encod: {}", rawPassword);
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        log.warn("matched: {}", rawPassword + ":" + encodedPassword);

        return rawPassword.toString().equals(encodedPassword);
    }
}
