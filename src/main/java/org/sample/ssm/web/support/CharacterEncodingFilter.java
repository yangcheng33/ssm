package org.sample.ssm.web.support;

import javax.servlet.*;
import java.io.IOException;

/**
 * 
 * 编码过滤，设置字符编码以解决中文乱码问题
 * 
 * @author HuQingmiao
 *  
 */
public class CharacterEncodingFilter implements Filter {

    protected FilterConfig filterConfig = null;

    protected String encoding = null;

    /**
     * 根据web.xml文件中的配置进行初始化
     */
    public void init(FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    /**
     * 设置编码
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        this.encoding = selectEncoding(request);
        if (encoding != null) {
            request.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    protected String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }

    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }

}

