package cn.settile.sblog.authorization;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author : lzjyz
 * @date : 2020-02-02 11:44
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
