package org.sample.ssm.web.filter;

import org.sample.ssm.web.support.XssHttpServletRequestWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * xss过滤器.
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
        if ("POST".equals(((HttpServletRequest) request).getMethod())) {
            chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
        } else {
            chain.doFilter(request, response);
        }
    }
}