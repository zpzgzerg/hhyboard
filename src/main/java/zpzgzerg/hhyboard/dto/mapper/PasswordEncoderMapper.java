package zpzgzerg.hhyboard.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import zpzgzerg.hhyboard.dto.mapper.annotation.EncodeMapping;

@Component
@RequiredArgsConstructor
public class PasswordEncoderMapper {

    private final PasswordEncoder passwordEncoder;

    @EncodeMapping
    public String encode(String value) {
        return passwordEncoder.encode(value);
    }
}
