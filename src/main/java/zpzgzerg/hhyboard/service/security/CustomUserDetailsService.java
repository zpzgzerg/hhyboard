package zpzgzerg.hhyboard.service.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zpzgzerg.hhyboard.entity.Member;
import zpzgzerg.hhyboard.repository.member.MemberRepository;
import zpzgzerg.hhyboard.security.config.CustomUser;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Member member = memberRepository.findByUserId(userId);

        if (member == null) {
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(member.getRoleType().name()));

        log.info("role : {} ", member.getRoleType().name());

        return new CustomUser(roles, member.getUserId(), member.getPassword(), member);
    }
}
