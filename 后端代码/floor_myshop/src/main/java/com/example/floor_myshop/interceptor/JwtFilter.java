package com.example.floor_myshop.interceptor;


import com.example.floor_myshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtFilter implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        final String token = request.getHeader("token");
//        if (token == null) {
//            new RuntimeException("无token字段 header").printStackTrace();
//            return false;
//        }
//        Claims claims = jwtUtil.parseJWT(token);
//        if (claims==null){
//            new RuntimeException("无法正确解析token").printStackTrace();
//            return false;
//        }
//        if (claims.getExpiration().before(new Date())) {
//            new RuntimeException("token 已过期").printStackTrace();
//            return false;
//        }
//        if("merchant".equals(claims.get("roles"))){//如果是用户
//            request.setAttribute("merchant_claims", claims);
//        }
//        return true;
//    }


}
