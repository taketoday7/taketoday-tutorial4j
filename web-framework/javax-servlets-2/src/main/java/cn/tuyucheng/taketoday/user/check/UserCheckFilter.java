package cn.tuyucheng.taketoday.user.check;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/user-check/*")
public class UserCheckFilter implements Filter {
   public static void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
      request.getRequestDispatcher("/WEB-INF/user.check" + page)
            .forward(request, response);
   }

   @Override
   public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
      if (!(req instanceof HttpServletRequest)) {
         throw new ServletException("Can only process HttpServletRequest");
      }

      if (!(res instanceof HttpServletResponse)) {
         throw new ServletException("Can only process HttpServletResponse");
      }

      HttpServletRequest request = (HttpServletRequest) req;
      HttpServletResponse response = (HttpServletResponse) res;

      request.setAttribute("origin", request.getRequestURI());

      if (!request.getRequestURI()
            .contains("login") && request.getSession(false) == null) {
         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
         forward(request, response, "/login.jsp");
         // we return here so the original servlet is not processed
         return;
      }

      chain.doFilter(request, response);
   }

   public void init(FilterConfig arg) throws ServletException {
   }
}