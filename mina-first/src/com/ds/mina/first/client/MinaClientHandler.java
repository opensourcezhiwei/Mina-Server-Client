package com.ds.mina.first.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaClientHandler extends IoHandlerAdapter {

	private static Logger log = LoggerFactory.getLogger(MinaClientHandler.class);

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		log.info("客户端连接session创建...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		log.info("客户端连接session打开...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		log.info("客户端连接session关闭...");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		log.info("客户端连接session空闲...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		log.error("客户端发生异常 : ", cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String msg = message + "";
		log.info("客户端接收到信息为 : " + msg);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
	}

}
