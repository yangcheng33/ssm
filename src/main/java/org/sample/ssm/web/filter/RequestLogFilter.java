package org.sample.ssm.web.filter;

import org.apache.commons.lang3.StringUtils;
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

/**
 * 请求记录过滤器.
 */
public class RequestLogFilter implements Filter {
    private final String[]     logHeaders   = { "PUT", "POST", "DELETE" };
    protected     Logger       log          = LoggerFactory.getLogger(this.getClass());
    private       FilterConfig filterConfig = null;

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

        if (StringUtils.containsAny(httpRequest.getMethod(), logHeaders)) {

            log.debug(">>>{} {}", httpRequest.getMethod(), httpRequest.getServletPath());
        }

        //请求路径 URL
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
