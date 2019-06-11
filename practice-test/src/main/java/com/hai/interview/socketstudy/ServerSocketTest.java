package com.hai.interview.socketstudy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Administrator
 * 服务端
 */
public class ServerSocketTest {
	public static void main(String[] args) {
		try {
			//初始化服务端socket并且绑定5325端口
			ServerSocket serverSocket = new ServerSocket(5325);
			//等待客户端的连接
			System.out.println("---等待client连接---");
			Socket socket = serverSocket.accept();
			System.out.println("---连接成功---");
			//获取输入流，并且指定统一的编码格式
			BufferedReader bufferedReader = 
					new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			String str;
			
			//通过while循环不断读取信息
			while((str = bufferedReader.readLine())!=null) {
				System.out.println("client："+str);
			}
		} catch (Exception e) {
			System.out.println("Error:"+e);
		}
	}

}
