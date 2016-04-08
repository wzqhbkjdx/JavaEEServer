package getdata;

import java.util.Calendar;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import utils.MyConstants;

public class StatisticsTask extends TimerTask {
	
	private static final int STATISTICS_SCHEDULE_HOUR = 0;
    private static boolean isRunning = false;
    private ServletContext context = null;
    
    public StatisticsTask(ServletContext context)
    {
        this.context = context;
    }
	

	@Override
	public void run() {
		Calendar cal = Calendar.getInstance(); 
        //System.out.println(isRunning);
        if (!isRunning) 
        { 
            
                isRunning = true; 
                context.log("开始执行指定任务");

                //TODO 添加自定义的详细任务
                executeTask();

                //指定任务执行结束
                isRunning = false;
                context.log("指定任务执行结束"); 
            
        } 
        else 
        {
            context.log("上一次任务执行还未结束");
        }
		
	}


	private void executeTask() {
		long start = System.currentTimeMillis();
		DataResolver.dataResolve(MyConstants.RSSURLZGHKWLSJDHZ,"中国航空新闻网","/Users/bym/xmldoc/",MyConstants.CATEGORYZGHKWLSJDHZ);
		DataResolver.dataResolve(MyConstants.RSSURLZGHKWNEWSCENTER,"中国航空新闻网","/Users/bym/xmldoc2/",MyConstants.CATEGORYZGHKWNEWSCENTER);
		DataResolver.picDataResolve("/Users/bym/Documents/NewFile.xml", "中国航空新闻网", 1);
		
		
//		DataResolver.dataResolve(MyConstants.RSSURLZGHKWLSJDHZ,"中国航空新闻网","/alidata/xmldoc/",MyConstants.CATEGORYZGHKWLSJDHZ);
//		DataResolver.dataResolve(MyConstants.RSSURLZGHKWNEWSCENTER,"中国航空新闻网","/alidata/xmldoc2/",MyConstants.CATEGORYZGHKWNEWSCENTER);
		
		long end = System.currentTimeMillis();
		System.out.println("获取rss并保存数据共耗时：" + (end - start)/1000);
		
	} 

}
