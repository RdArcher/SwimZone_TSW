package control;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


public class MainContext implements ServletContextListener {
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		DataSource ds = null;
		
		try {
			Context init = new InitialContext();
			Context env = (Context) init.lookup("java:comp/env");
			ds = (DataSource) env.lookup("jdbc/swimzone_db");
		} catch(NamingException e) {
			System.out.println("Errore:" + e.getMessage());
		}
		
		context.setAttribute("DataSource", ds);
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
}
