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
package com.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * XXXXXXXXXXXXXXXXXXXXX
 *
 * @author 袁忠兴 at 2017/6/13 17:46。
 */
public class App {

    public static void main( String[] args ) throws Exception
    {
        ClassPathXmlApplicationContext context=
                new ClassPathXmlApplicationContext(new String[] {"applicationProvider.xml"});
        context.start();
        System.out.println("任意键退出");
        System.in.read();
    }
}