package com.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.util.Date;

/**
 * Created by SamZhao on 2017/5/22.
 *
 * 业务对象 需要继承IoHandlerAdapter
 *
 */
public class MyHandler extends IoHandlerAdapter{

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("--->sessionCreated");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("--->sessionOpened");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("--->sessionClosed");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("--->sessionIdle");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("捕获到异常--->exceptionCaught");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String msg = (String) message;
        Date date = new Date();
        System.out.println("服务端接收到数据--->messageReceived Date:" + date + ", 内容: " + msg );

        if("exit".equals(msg))
            session.close();
        else
            session.write(date);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("发送数据--->messageSent");
    }
}
