package com.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * Created by SamZhao on 2017/5/22.
 */
public class MyClientHandler extends IoHandlerAdapter {
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("客户端异常--->exceptionCaught");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String msg = (String) message;
        System.out.println("客户端收到数据--->messageReceived   msg: " + msg );
    }
}
