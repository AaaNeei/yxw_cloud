package com.yxw.yxw_eureka.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * eureka安全登录问题,
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
        // Configure HttpSecurity as needed (e.g. enable http basic).
        /*http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);

        http.csrf().disable();
        //注意：为了可以使用 http://${user}:${password}@${host}:${port}/eureka/ 这种方式登录,所以必须是httpBasic,
        // 如果是form方式,不能使用url格式登录
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();*/
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //配置需要忽略验证的地址
        web.ignoring().antMatchers("/eureka/**");
    }

}