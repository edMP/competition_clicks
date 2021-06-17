package es.iserafaelalberti.daw.dwes.murielpoussaedaurdo.competition_clicks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CompetitionClicksApplication {

    public static void main(String[] args) {SpringApplication.run(CompetitionClicksApplication.class, args);}
    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws  Exception {
            http.cors().disable().csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/").permitAll();
        }
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){return new BCryptPasswordEncoder();}

}
