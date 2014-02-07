
package es.microforum.servicefrontend;

import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Servlet 3.0 {@link WebApplicationInitializer} to setup both a root {@link WebApplicationContext} using the
 * {@link ApplicationConfig} class for repository setup. Beyond that it will deploy a
 * {@link RepositoryRestExporterServlet} to export the repositories defined in {@link ApplicationConfig} via a REST
 * interface.
 * 
 * @author Oliver Gierke
 */
public class RestWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ApplicationConfig.class };
	}

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { RepositoryRestMvcConfiguration.class };
	}

	/* 
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
