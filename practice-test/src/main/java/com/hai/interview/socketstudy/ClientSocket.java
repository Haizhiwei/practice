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
 * https://www.jianshu.com/p/cde27461c226
 * 
 */
public class ClientSocket {
	public static void main(String[] args) {
		try {
			// 向本机5325端口发出客户请求
			Socket socket = new Socket("127.0.0.1", 5325);
			//通过socket获取字符流
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//通过标准输入获取字符流
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
			//通过一个while 循环，实现客户端不间断的通过标准输入流读取来的消息，发送给服务端。
			//且双方约定一个标识（\n），当客户端发送一个标识给服务端时，表明客户端端已经完成一个数据的载入。
			//而服务端在结束数据的时候，也通过这个标识进行判断，如果接受到这个标识，表明数据已经传入完成，那么服务端就可以将数据度入后显示出来
			while(true) {
				String str = bufferedReader.readLine();
				bufferedWriter.write(str);
				//双方约定一个标识（\n），当客户端发送一个标识给服务端时，表明客户端端已经完成一个数据的载入。
				bufferedWriter.write("\n");
				bufferedWriter.flush();
				
			}

			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
//简单版,clien输入,server输出（一对一，且仅一次）
//try {
//	// 向本机5325端口发出客户请求
//	Socket socket = new Socket("127.0.0.1", 5325);
//	/**
//	 * System.in为系统基本输入输出,
//	 * 即键盘输入.调用System.in方法获得输入字符串,并用该字符串作为参数生成一个InputStreamReader对象in
//	 * 由系统标准输入设备构造BufferedReader对象
//	 */
//	BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//	System.out.println("请输入你要发送的内容：");
//	Scanner scanner = new Scanner(System.in);
//	if (scanner.hasNextLine()) {
//		String str = scanner.nextLine();
//		bufferedWriter.write(str);
//	}
//	// 刷新输入流
//	bufferedWriter.flush();
//	socket.shutdownOutput();
//	bufferedWriter.close();
//} catch (IOException e) {
//	e.printStackTrace();
//}


//一对一版
