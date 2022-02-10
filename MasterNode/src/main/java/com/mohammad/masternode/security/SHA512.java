package com.mohammad.masternode.security;

import com.google.common.hash.Hashing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

public class SHA512 implements PasswordEncoder {


    @Override
    public String encode(CharSequence password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }


}
