package webapplisteners;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Hibernate_SF_Manager implements ServletContextListener {



    public Hibernate_SF_Manager() {
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("in ctx sf created");
        utils.HibernateUtils.getSf();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("in ctx destroyed");
        utils.HibernateUtils.getSf().close();
    }

}
