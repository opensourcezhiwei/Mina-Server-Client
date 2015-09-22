package com.ds.mina.first.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaClient {

	private static Logger log = LoggerFactory.getLogger(MinaClient.class);

	private static final int PORT = 9999;
	private static final String HOST = "127.0.0.1";

	public static void main(String[] args) {
		// 创建一个非阻塞客户端程序
		NioSocketConnector connector = new NioSocketConnector();
		// 设置连接超时时间
		connector.setConnectTimeoutMillis(10 * 60 * 1000);
		// 添加过滤器
		connector.getFilterChain()//
				.addLast("codec", new ProtocolCodecFilter(//
						new TextLineCodecFactory(//
								Charset.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
		//添加业务处理逻辑
		connector.setHandler(new MinaClientHandler());
		//创建连接
		ConnectFuture conn = connector.connect(new InetSocketAddress(HOST, PORT));
		//等待连接完成
		conn.awaitUninterruptibly();
		IoSession session = conn.getSession();//获得session
		session.write("伟少 like mina ");//发送消息
	}
}
