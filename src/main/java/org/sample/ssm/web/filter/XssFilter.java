package org.sample.ssm.web.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * xss filter
 *
 * @author Yang Cheng
 * @date 2016-12-07
 */
public class XssFilter implements Filter {
    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if ("POST".equals(((HttpServletRequest)request).getMethod())) {
            chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
        } else {
            chain.doFilter(request, response);
        }
    }
}