package org.sample.ssm.web.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by HuQingmiao on 2015-5-20.
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

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        String servletPath = httpRequest.getServletPath();
        if (!servletPath.endsWith(".css") && !servletPath.endsWith(".js") && !servletPath.endsWith(".woff")) {
            log.info(">>> " + servletPath);
        }

        request.setAttribute("base", httpRequest.getContextPath());

        //请求路径 URL
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
