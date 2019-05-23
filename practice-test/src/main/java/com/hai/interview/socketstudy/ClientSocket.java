package com.hai.interview.socketstudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * socket介绍：
 * 网络上两个程序通个一个双向的通讯连接实现通信（TCP/IP）,这个双向的链路的一端被称为一个socket。socket一般用来实现客户端和服务端的通信连接。
 * socket通信是一种基于TCP/IP网络层上的一种传送方式。 对于一个功能齐全的Socket，都要包含以下基本结构，其工作过程包含以下四个基本的步骤：
 * 1.创建Socket; 2.打开连接到Socket的输入/出流 3.按照一定的协议对Socket进行读/写操作 4.关闭Socket
 * 
 * @author Administrator 客户端
 */
public class ClientSocket {
	public static void main(String[] args) {
		try {
			// 向本机5325端口发出客户请求
			Socket socket = new Socket("127.0.0.1", 5325);
			/**
			 * System.in为系统基本输入输出,
			 * 即键盘输入.调用System.in方法获得输入字符串,并用该字符串作为参数生成一个InputStreamReader对象in
			 * 由系统标准输入设备构造BufferedReader对象
			 */
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.println("请输入你要发送的内容：");
			Scanner scanner = new Scanner(System.in);
			if (scanner.hasNextLine()) {
				String str = scanner.nextLine();
				bufferedWriter.write(str);
			}
			// 刷新输入流
			bufferedWriter.flush();
			socket.shutdownOutput();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
