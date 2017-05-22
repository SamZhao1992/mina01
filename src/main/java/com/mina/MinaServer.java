package com.mina;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by SamZhao on 2017/5/22.
 *
 * mina 服务端
 *
 */
public class MinaServer {

    private static int port = 7080;
    static IoAcceptor ioAcceptor = null;

    public static void main(String[] args) {
        try {
            ioAcceptor = new NioSocketAcceptor();
            //设置编码过滤器
            ioAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                    new TextLineCodecFactory(Charset.forName("UTF-8"),
                            LineDelimiter.WINDOWS.getValue(),
                            LineDelimiter.WINDOWS.getValue())));
            //设置缓冲区大小
            ioAcceptor.getSessionConfig().setReadBufferSize(1024);
            //设置多少时间内没有读写操作 空闲时间  10ms
            ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
            ioAcceptor.setHandler(new MyHandler());
            //绑定端口号---服务端开始监听工作
            ioAcceptor.bind(new InetSocketAddress(port));
            System.out.println("MinaServer start---> " + port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
