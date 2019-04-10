/*************************************************************************
 *                  HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 *
 *                COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 *    ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 *                  HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 *************************************************************************/
package test;

import com.test.DemoTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * XXXXXXXXXXXXXXXXXXXXX
 *
 * @author 袁忠兴 at 2017/6/13 17:49。
 */
public class ConsumerThd {
    public void sayHello(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(
                new String[] {"applicationConsumer.xml"});
        context.start();
        DemoTest demoService=(DemoTest) context.getBean("demoService");
        System.out.println(demoService.hello("袁忠兴"));
    }
}