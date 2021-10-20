package oit.is.z1017.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Lec03AuthConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("ほんだ").password(passwordEncoder().encode("123")).roles("USER");

    // $ sshrun htpasswd -nbBC 10 master 1192
    // pass: 1192
    auth.inMemoryAuthentication().withUser("master")
        .password("$2y$10$WtUytKKWrPRVlFUhG4xWt.Gu3EDeiTDTpwEsutfns67jt4NbIu2D2").roles("ADMIN");
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Spring Securityのフォームを利用してログインを行う
    http.formLogin();
    http.authorizeRequests().antMatchers("/lec02/**").authenticated();
    // Spring Securityの機能を利用してログアウト．ログアウト時は http://localhost:8080/ に戻る
    http.logout().logoutSuccessUrl("/");
    http.csrf().disable();
    http.headers().frameOptions().disable();
  }
}
