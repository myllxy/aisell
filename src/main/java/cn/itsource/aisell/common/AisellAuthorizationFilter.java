package cn.itsource.aisell.common;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义权限过滤器
 *
 * @author myllxy
 * @create 2019-12-17 18:51
 */
public class AisellAuthorizationFilter extends PermissionsAuthorizationFilter {
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
            saveRequestAndRedirectToLogin(request, response);
        } else {
           /*
              <!--如果你没有权限，你会进入这个页面-->
              <property name="unauthorizedUrl" value="/s/unauthorized.jsp"/>
            */
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            String xhr = req.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(xhr)) {
                resp.setContentType("application/json;charset=UTF-8");
                // 是Ajax请求，返回json数据
                resp.getWriter().print("{\"success\":false,\"msg\":\"没有权限\"}");
            } else {
                // 不是Ajax请求和原来处理方案一样
                // 拿到配置的没有权限的跳转路径
                String unauthorizedUrl = this.getUnauthorizedUrl();
                if (StringUtils.hasText(unauthorizedUrl)) {
                    // 如果有跳转路径 -> 就进行跳转
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    // 如果没有配置路径  -> 报401错误
                    WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
        return false;
    }
}
