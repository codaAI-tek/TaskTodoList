package com.book.snow.acl.controller;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.book.snow.acl.service.IGoogleService;
import com.book.snow.common.result.JsonResult;
import com.book.snow.common.utils.JwtUtuil;
import com.book.snow.model.user.GoogleUser;
import com.book.snow.vo.GoogleLoginVo;
import com.xkcoding.http.config.HttpConfig;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthGoogleRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/oauth")
public class RestAuthController {
    @Autowired
    IGoogleService googleService;


    @RequestMapping("/render/{source}")
    public JsonResult renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());

        Map<String,Object> map = new HashMap<>();
        map.put("loginUrl",authorizeUrl);

        return JsonResult.ok(map);
    }

    @RequestMapping("/test")
    public String testAuth(HttpServletResponse response) throws IOException {
        return "hhhhhh";
    }

    @RequestMapping("/callback/{source}")
    public JsonResult login(AuthCallback callback) {

        AuthRequest authRequest = getAuthRequest();
        AuthResponse response = authRequest.login(callback);
        AuthUser authUser = (AuthUser) response.getData();

        GoogleUser user = new GoogleUser();
        user.setAvatar(authUser.getAvatar());
        user.setEmail(authUser.getEmail());
        user.setUserAccount(authUser.getEmail());
        user.setLocation(authUser.getLocation());
        user.setNickName(authUser.getNickname());
        user.setSex(authUser.getGender().toString());

        List<GoogleUser> list = googleService.selectByEmail(user.getEmail());
        if(list.size() <= 0){
            boolean save = googleService.save(user);
            if(save){
                user.setToken(JwtUtuil.createToken(user));
                return JsonResult.ok(user);
            }else {
                return JsonResult.fail(null);
            }
        }else{
            user.setToken(JwtUtuil.createToken(list.get(0)));
            return JsonResult.ok(user);
        }

    }

    private AuthRequest getAuthRequest() {
        return new AuthGoogleRequest(AuthConfig.builder()
//                .clientId("534909237954-bt3cvj7l0u5ougm5jof4rll6c0fkms54.apps.googleusercontent.com")
//                .clientSecret("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")
//                .redirectUri("http://www.akaywj.cloudns.be/plugin/auth/google")
                .clientId("894916106231-2rlf1s98a1kcf54ts4mih8t70ts9f4eq.apps.googleusercontent.com")
                .clientSecret("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")
                .redirectUri("http://localhost:6001/api/oauth/callback/google")
                 //针对国外平台配置代理
                .httpConfig(HttpConfig.builder()
                        .timeout(15000)
                        // host 和 port 请修改为开发环境的参数
                        .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)))
                        .build())
                .build());
    }

}
