package com.mina;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by SamZhao on 2017/5/22.
 * <p>
 * <p>
 * Mina 客户端
 */
public class MinaClient {

    private static String host = "127.0.0.1";
    private static int port = 7080;

    public static void main(String[] args) {
        IoSession ioSession = null;
        IoConnector ioConnector = new NioSocketConnector();
        //ioConnector超时时间
        ioConnector.setConnectTimeout(3000);
        //设置过滤器  编码过滤
        ioConnector.getFilterChain().addLast("coderc", new ProtocolCodecFilter(
                new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue())));
        ioConnector.setHandler(new MyClientHandler());
        ConnectFuture connectFuture = ioConnector.connect(new InetSocketAddress(host, port));
        connectFuture.awaitUninterruptibly();//等待连接
        ioSession = connectFuture.getSession();
        ioSession.write("nihao from MinaClient...");
        ioSession.getCloseFuture().awaitUninterruptibly();//等待关闭连接
        ioConnector.dispose();

    }


}
