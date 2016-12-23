package hei.devweb.project.servlets;

import java.util.Locale;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public abstract class AbstractGenericServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected TemplateEngine createTemplateEngine(HttpServletRequest request){
		Locale.setDefault(Locale.ENGLISH);
		ServletContextTemplateResolver templateresolver = new ServletContextTemplateResolver(request.getServletContext());
		templateresolver.setTemplateMode(TemplateMode.HTML);
		templateresolver.setPrefix("/WEB-INF/templates/");
		templateresolver.setCharacterEncoding("UTF-8");
		
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addDialect(new Java8TimeDialect());
		templateEngine.setTemplateResolver(templateresolver);
		
		return templateEngine;
	}
}
