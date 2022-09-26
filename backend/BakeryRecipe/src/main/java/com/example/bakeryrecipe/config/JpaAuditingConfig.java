package com.example.bakeryrecipe.config;

import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

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
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return memberRepository.findOneByUsername(username);
    }
}
