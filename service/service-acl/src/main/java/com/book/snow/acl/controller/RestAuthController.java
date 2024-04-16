package com.task.todolist.controller;

import cn.hutool.core.date.DateTime;
import com.task.todolist.entity.User;
import com.task.todolist.mapper.UserMapper;
import com.task.todolist.service.IUserService;
import com.task.todolist.util.JsonResult;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthGoogleRequest;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
//

@RestController
@RequestMapping("/oauth")
public class RestAuthController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    IUserService userService;

    @RequestMapping("/render/{source}")
    public JsonResult<String> renderAuth(HttpServletResponse response) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
//        response.sendRedirect(authorizeUrl);
        JsonResult<String> result = new JsonResult<>();
        result.setState(200);
        result.setData(authorizeUrl);
        return result;
    }
//    @PostMapping()
//    public JsonResult<String> renderAuth(@Req){}


    @RequestMapping("/test")
    public String testAuth(HttpServletResponse response) throws IOException {
        return "hhhhhh";
    }

    @RequestMapping("/callback/{source}")
    public JsonResult<User> login(AuthCallback callback) {

        AuthRequest authRequest = getAuthRequest();
        AuthResponse response = authRequest.login(callback);

        AuthUser authUser = (AuthUser) response.getData();
        JsonResult<User> result = new JsonResult<>();
        User user = new User();
        user.setUserName(authUser.getNickname());
        user.setUserAcount(authUser.getEmail());
        user.setEmail(authUser.getEmail());
        user.setUserId(authUser.getEmail());
        user.setAvatar(authUser.getAvatar());
        user.setCreatedAt(new DateTime());
        user.setIsDelete(0);
        User byUserAccount = userMapper.findByUserAccount(user.getUserAccount());
        System.out.println(user);
        if(byUserAccount == null){
            try{
                Integer insert = userMapper.insert(user);
            }catch (Exception e){
                System.out.println(e);
            }
            User loginByGoogle = userService.loginByGoogle(user);
            result.setData(loginByGoogle);
            result.setMsg("登陆成功");
            result.setState(200);
            return result;
        }
        User user1 = userService.loginByGoogle(user);
        result.setData(user1);
        result.setMsg("登陆成功");
        result.setState(200);
        return result;
    }

    private AuthRequest getAuthRequest() {
        return new AuthGoogleRequest(AuthConfig.builder()
                .clientId("534909237954-bt3cvj7l0u5ougm5jof4rll6c0fkms54.apps.googleusercontent.com")
                .clientSecret("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")
                .redirectUri("http://www.akaywj.cloudns.be/plugin/auth/google")
//                .clientId("894916106231-2rlf1s98a1kcf54ts4mih8t70ts9f4eq.apps.googleusercontent.com")
//                .clientSecret("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")
//                .redirectUri("http://localhost:6001/api/oauth/callback/google")
//                 针对国外平台配置代理
//                .httpConfig(HttpConfig.builder()
//                        .timeout(15000)
//                        // host 和 port 请修改为开发环境的参数
//                        .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)))
//                        .build())
                .build());
    }

//    @PostMapping("/check")
//    public void check(){
//
//
//        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
//                // Specify the CLIENT_ID of the app that accesses the backend:
//                .setAudience(Collections.singletonList(CLIENT_ID))
//                // Or, if multiple clients access the backend:
//                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
//                .build();
//
//// (Receive idTokenString by HTTPS POST)
//
//        GoogleIdToken idToken = verifier.verify(idTokenString);
//        if (idToken != null) {
//            Payload payload = idToken.getPayload();
//
//            // Print user identifier
//            String userId = payload.getSubject();
//            System.out.println("User ID: " + userId);
//
//            // Get profile information from payload
//            String email = payload.getEmail();
//            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
//            String name = (String) payload.get("name");
//            String pictureUrl = (String) payload.get("picture");
//            String locale = (String) payload.get("locale");
//            String familyName = (String) payload.get("family_name");
//            String givenName = (String) payload.get("given_name");
//
//            // Use or store profile information
//            // ...
//
//        } else {
//            System.out.println("Invalid ID token.");
//        }
//    }
}
