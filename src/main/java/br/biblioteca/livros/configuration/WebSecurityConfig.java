package br.biblioteca.livros.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // HABILITA O FRAME DO H2-CONSOLE
        http.headers().frameOptions().disable();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user/registration").permitAll()
                .antMatchers(HttpMethod.POST, "/user/registration").permitAll()
                .antMatchers(HttpMethod.GET, "/books/list").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.GET, "/user/list").hasRole("GERENCIA_USUARIOS")
                .antMatchers(HttpMethod.GET, "/user/alterar/*").hasAnyRole("GERENCIA_USUARIOS", "ATUALIZAR_DADOS")
                .antMatchers(HttpMethod.GET, "/user/excluir/*").hasAnyRole("GERENCIA_USUARIOS", "ATUALIZAR_DADOS")
                .antMatchers(HttpMethod.GET, "/books/novo").hasAnyRole("GERENCIA_USUARIOS", "ATUALIZAR_DADOS")
                .antMatchers(HttpMethod.GET, "/books/alterar/*").hasAnyRole("GERENCIA_USUARIOS", "ATUALIZAR_DADOS")
                .antMatchers(HttpMethod.POST, "/books/gravar").hasAnyRole("GERENCIA_USUARIOS", "ATUALIZAR_DADOS")
                .antMatchers(HttpMethod.GET, "/books/excluir/*").hasAnyRole("GERENCIA_USUARIOS", "ATUALIZAR_DADOS")
                .antMatchers(HttpMethod.GET, "/authors/list").hasRole("VISUALIZAR")
                .antMatchers(HttpMethod.GET, "/authors/novo").hasAnyRole("GERENCIA_USUARIOS", "ATUALIZAR_DADOS")
                .antMatchers(HttpMethod.GET, "/authors/alterar/*").hasAnyRole("GERENCIA_USUARIOS", "ATUALIZAR_DADOS")
                .antMatchers(HttpMethod.POST, "/authors/gravar").hasAnyRole("GERENCIA_USUARIOS", "ATUALIZAR_DADOS")
                .antMatchers(HttpMethod.GET, "/authors/excluir/*").hasAnyRole("GERENCIA_USUARIOS", "ATUALIZAR_DADOS")
                .and()
                .formLogin()
                .loginPage("/user/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}
