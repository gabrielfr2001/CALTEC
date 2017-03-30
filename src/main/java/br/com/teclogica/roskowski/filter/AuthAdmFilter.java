package br.com.teclogica.roskowski.filter;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.teclogica.roskowski.interfaces.IManterUsuarioSBean;
import br.com.teclogica.roskowski.sbean.ManterUsuarioSBean;

@WebFilter("/pages/admin/*")
public class AuthAdmFilter implements Filter{
	@EJB
	private IManterUsuarioSBean sBean = new ManterUsuarioSBean();
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		String loginURL = request.getContextPath() + "/pages/unautorized.jsf";

		if (session == null && !request.getRequestURI().equals(loginURL)) {
			response.sendRedirect(loginURL);
		} else if (!sBean.carregarUsuario(session.getAttribute("user").toString()).getUsuario().equals("gabrielfr2001")) {
			response.sendRedirect(loginURL);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
