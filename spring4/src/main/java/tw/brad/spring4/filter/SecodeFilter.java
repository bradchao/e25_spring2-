package tw.brad.spring4.filter;

import jakarta.servlet.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Order(1)
//@Component
public class SecodeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Second:before");
        chain.doFilter(request, response);
        System.out.println("Second:after");
    }
}
