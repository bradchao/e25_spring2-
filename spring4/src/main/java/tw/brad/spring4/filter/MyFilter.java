package tw.brad.spring4.filter;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
public class MyFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter:before");
        chain.doFilter(request, response);
        System.out.println("MyFilter:after");
    }
}
