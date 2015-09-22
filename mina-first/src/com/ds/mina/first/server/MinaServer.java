package com.ds.mina.first.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaServer {

	private static final Logger log =  LoggerFactory.getLogger(MinaServer.class);
	
	private static final int PORT = 9999;
	
	public static void main(String[] args) throws Exception {
		// 创建一个非阻塞的server端的socket
		NioSocketAcceptor acceptor = new NioSocketAcceptor();
		// 设置过滤器(使用mina提供的文本换行符编解码器)
		acceptor.getFilterChain().//
				addLast("codec", new ProtocolCodecFilter(//
						new TextLineCodecFactory(//
								Charset.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
		//设置读取数据的缓冲区大小
		acceptor.getSessionConfig().setReadBufferSize(2048);
		//读写通道10秒内无操作进入空闲状态
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		//编写逻辑处理器
		acceptor.setHandler(new MinaServerHandler());
		//绑定端口
		acceptor.bind(new InetSocketAddress(PORT));
		log.info("mina 服务器启动 ... 端口号为 : " + PORT);

	}

}
