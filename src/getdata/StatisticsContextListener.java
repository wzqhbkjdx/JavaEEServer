package getdata;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * 以注解的方式将Listener写入web.xml文件
 * @author bym
 *
 */

@WebListener  
public class StatisticsContextListener implements ServletContextListener {
	
	private java.util.Timer timer = null;
	
	/**
     * 这个方法在Web应用服务做好接受请求的时候被调用。
     * 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		timer = new java.util.Timer(true);
        event.getServletContext().log("定时器已启动"); 
        timer.schedule(new StatisticsTask(event.getServletContext()), 0, 40*1000);//每隔40秒调用一次
        event.getServletContext().log("已经添加任务调度表");
		
	}
	

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
