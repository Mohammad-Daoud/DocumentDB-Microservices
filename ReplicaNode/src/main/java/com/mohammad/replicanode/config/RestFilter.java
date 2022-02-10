package com.mohammad.replicanode.config;

import javax.servlet.*;
import java.io.IOException;

public class RestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(request, response);
    }

}
