/**
 * Author: AKAYWJ
 * Date:2024/4/5 下午6:34
 */
package com.book.snow.acl.intercaptor;


import com.alibaba.csp.sentinel.util.StringUtil;
import com.book.snow.acl.service.ILogService;
import com.book.snow.common.utils.JwtUtuil;
import com.book.snow.model.Log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Configuration
public class TokenFilterInterCaptor implements HandlerInterceptor {
    @Autowired
    ILogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getMethod().equals(RequestMethod.OPTIONS.name()))
        {
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Access-Control-Allow-Headers","*");
            response.setHeader("Access-Control-Allow-Methods","*");
            response.setHeader("Access-Control-Allow-Credentials","true");
            response.setHeader("Access-Control-Max-Age","3600");
            response.setStatus(HttpStatus.OK.value());
            return true;
        }

        // 获取客户端IP地址
        String ip = request.getRemoteAddr();
        // 获取客户端主机名
        String host = request.getRemoteHost();
        // 获取客户端端口
        String port = String.valueOf(request.getRemotePort());
        // 获取请求的URI
        String path = request.getRequestURI();
        HttpSession httpSession = request.getSession();
        //获取请求次数
        Integer count = (Integer) httpSession.getAttribute("count");

        //判断IP是否存在
        List<Log> logs = logService.selectByIp(ip);
        List<Log> logPath = logService.selectByPath(path);

        if(logs.size() > 0 && logPath.size() > 0){
            logPath.get(0).setCount(logPath.get(0).getCount() + 1);
            logService.updateById(logPath.get(0));
        }else{
            Log log = new Log();
            log.setHost(host);
            log.setIp(ip);
            log.setPath(path);
            log.setPort(port);
            log.setUrl(path);
            log.setCount(count);
            logService.save(log);
        }

        String token = request.getHeader("Authorization");

        if(StringUtil.isEmpty(token) || !JwtUtuil.checkToken(token)){
            response.setStatus(403);
            // 设置响应内容类型
            response.setContentType("application/json");
            //设置响应内容
            try {
                response.getWriter().write("{\"error\":\"Your token has expired.\"}");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        return true;
    }
}
