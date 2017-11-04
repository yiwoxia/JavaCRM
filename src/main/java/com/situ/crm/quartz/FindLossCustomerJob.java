package com.situ.crm.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.situ.crm.service.ICustomerService;

@Component
public class FindLossCustomerJob {
	
	@Autowired
	private ICustomerService customerService;//客户Service

	   /**
     * 每天凌晨2点定期执行
     */
   /* @Scheduled(cron = "0/10 * * * * ?")*/
	@Scheduled(cron = "0 0 2 * * ?")
    public void work() {
       System.out.println("FindLossCustomerJob每天凌晨2点定期执行");
       customerService.checkCustomerLoss();
    }


}
