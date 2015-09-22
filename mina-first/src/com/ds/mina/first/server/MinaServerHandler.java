package com.ds.mina.first.server;

import java.util.Date;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaServerHandler extends IoHandlerAdapter {

	private static Logger log = LoggerFactory.getLogger(MinaServerHandler.class);

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		log.info("服务端与客户端创建连接...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		log.info("服务端与客户端连接打开...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		log.info("服务端进入空闲状态...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		log.info("服务端发生异常...", cause);
	}

	/**
	 * 自定义的业务逻辑处理器继承了IoHandlerAdapter类，它默认覆盖了父类的7个方法，<br>
	 * 其实我们最关心最常用的只有一个方法：<br>
	 * messageReceived() ---- 服务端接收到一个消息后进行业务处理的方法；看代码
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String msg = message + "";
		log.info("服务端收到的数据为 : " + msg);

		// 服务器接收断开连接指令-->根据这个可以自定义定制一套cmd
		if ("bye".equalsIgnoreCase(msg)) {
			log.info("服务器接收连接断开...");
			session.close(true);
		}

		Date d = new Date();
		session.write(d);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// 发送成功后主动断开与客户端的连接
		session.close(true);
		log.info("服务端信息发送成功...");
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
	}

}
