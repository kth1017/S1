package pracs1.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pracs1.springboot.domain.user.Role;

@RequiredArgsConstructor
@EnableWebSecurity // 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2 console 사용을 위한 비활성화
                .and()
                .authorizeRequests() //url별 권한 관리 시작
//                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile", "/search").permitAll()
//                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .antMatchers("/api/link/**").hasRole(Role.ADMIN.name())
                .anyRequest().permitAll() // 이외 url은 인증사용자 설정
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint() // 로그인 성공 후 정보로딩시 설정
                .userService(customOAuth2UserService); // 서비스 구현체 설정
    }
}
