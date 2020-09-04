package com.xiong.user.config;
import com.xiong.user.filter.JwtTokenFilter;
import com.xiong.user.filter.VerificationCodeFilter;
import com.xiong.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;
    @Autowired
    private CustomFilterInvocationSecurityMetadataSource metadataSource;
    @Autowired
    private CustomUrlDecisionManager decisionManager;
    @Autowired
    private CustomAccessDeniedHandler deniedHandler;
    @Autowired
    private CustomAuthenticationEntryPoint entryPoint;
    @Autowired
    private VerificationCodeFilter verificationCodeFilter;
    @Autowired
    private CustomSessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public JwtTokenFilter authenticationTokenFilterBean(){
        return new JwtTokenFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/role/**","/user/sms/**", "/user/register", "/swagger-ui.html/**");
    }

    /**
     * 配置拦截规则、表单登录、登录成功或失败响应等
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 使用 JWT，关闭session
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
//                        object.setAccessDecisionManager(decisionManager); //决策管理器
//                        object.setSecurityMetadataSource(metadataSource); //安全元数据源
//                        return object;
//                    }
//                })
                .and()
                .formLogin()
                .permitAll()
                .loginProcessingUrl("/user/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler) //登录成功处理逻辑
                .failureHandler(failureHandler) //登录失败处理逻辑
                .and()
                .logout()
                .permitAll()
                .logoutUrl("/user/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(deniedHandler) // 权限拦截器，提示用户没有当前权限
                .authenticationEntryPoint(entryPoint); //匿名用户访问无权限资源时的异常处理
//                .and()
//                .sessionManagement()
//                .maximumSessions(1) //同一账号同时登录最大用户数
//                .expiredSessionStrategy(sessionInformationExpiredStrategy);
        http.addFilterBefore(verificationCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();  //禁用页面缓存
    }
}
