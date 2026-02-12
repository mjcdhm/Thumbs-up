package com.example.thumbsup.interceptor;

import com.example.thumbsup.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      String authHeader = request.getHeader("Authorization");
      //校验格式
      if (authHeader != null && authHeader.startsWith("Bearer ")) {
          response.setStatus(401);
          response.getWriter().write("Unauthorized:No token found");
          return false;
    }
      String token = authHeader.substring(7);
     //解析token
     String uerId=jwtUtil.parseToken(token);
     //解析结果
     if(uerId==null){
         response.setStatus(401);
         response.getWriter().write("Unauthorized:Invalid or expired token");
         return false;
     }
     request.setAttribute("current_user_id",uerId);
     return true;
    }

}
