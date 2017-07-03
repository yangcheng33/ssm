package org.sample.ssm.web.filter;

import org.sample.ssm.common.constant.SessionConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 请求记录过滤器.
 */
public class SessionFilter implements Filter {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String servletPath = httpRequest.getServletPath();
        if ("POST".equals(httpRequest.getMethod())) {

            log.debug(">>> POST " + servletPath);
            StringBuilder params = new StringBuilder();
            for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                params.append(entry.getKey()).append("=");
                if (entry.getValue().length > 0) {
                    params.append(entry.getValue()[0]).append(" ");
                } else {
                    params.append(" ");
                }
            }
            log.debug("\t参数: " + params.toString());
            log.debug("\t操作者: " + ((HttpServletRequest) request).getSession().getAttribute(SessionConst.NAME));
        }

        //请求路径 URL
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
