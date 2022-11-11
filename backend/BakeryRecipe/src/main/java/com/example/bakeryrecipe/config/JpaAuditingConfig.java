package com.example.bakeryrecipe.config;

import com.example.bakeryrecipe.authentication.UserDetailsImpl;
import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static java.util.Objects.isNull;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
	@Bean
    public AuditorAware<Member> auditorProvider() {
        return new AuditorAwareImpl();
    }
}

class AuditorAwareImpl implements AuditorAware<Member> {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Optional<Member> getCurrentAuditor() {
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (isNull(userDetails)) {
            return null;
        }
        Member member = new Member(userDetails.getId());
        return Optional.of(member);
    }
}
