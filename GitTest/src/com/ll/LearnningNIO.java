package com.ll;
import java.nio.*;
import java.nio.channels.*;
import java.io.*;


public class LearnningNIO {

	public static void main(String[] args)throws IOException {

//		method1();
//		method2();
//		method3();
//		method4();
//		channelMethod1();
	}
	
	public static void method1()
	{

		IntBuffer intBuf=IntBuffer.allocate(10);
		
		System.out.println("1.写入数据之前的position、limit、capacity:");
		
		System.out.println("position="+intBuf.position()+" limit="+intBuf.limit()+" capacity="+intBuf.capacity());
		
		intBuf.put(3);
		
		int[] arr=new int[] {1,7,9};
		
		intBuf.put(arr);
		
		System.out.println("2.写入数据之后的position、limit、capacity:");
		
		System.out.println("position="+intBuf.position()+" limit="+intBuf.limit()+" capacity="+intBuf.capacity());
		
		intBuf.flip();//重设缓冲区
		
		System.out.println("3.准备输出数据时的position、limit、capacity:");
		
		System.out.println("position="+intBuf.position()+" limit="+intBuf.limit()+" capacity="+intBuf.capacity());
		
		System.out.println("缓冲区中的内容：");
		
		while(intBuf.hasRemaining()) {
			
			int x=intBuf.get();
			
			System.out.println(x+"、");
			
		}
		
	}

//创建子缓冲区
	public static void method2() {
		
		IntBuffer buf=IntBuffer.allocate(10);
		
		IntBuffer sub=null;
		
		for(int i=0;i<10;i++) {
			
			buf.put(2*i+1);
			
		}
		buf.position(2);
		
		buf.limit(6);
		
		sub=buf.slice();//开辟子缓冲区
		
		for(int i=0;i<sub.capacity();i++) {
			
			int temp=sub.get(i);
			sub.put(temp-1);
			
		}
		
		buf.flip();
		
		buf.limit(buf.capacity());
		
		System.out.println("主缓冲区的内容：");
		
		while(buf.hasRemaining()) {
			
			int x=buf.get();
			
			System.out.println(x+"、");
			
		}
		
		
		
		
	}
	
//创建只读缓冲区
	public static void method3() {
		
		
		IntBuffer buf=IntBuffer.allocate(10);
		
		IntBuffer read=null;
		
		for(int i=0;i<10;i++) {
			
			buf.put(2*i+1);
			
		}
		
		read=buf.asReadOnlyBuffer();
		
		read.flip();
		
		System.out.println("缓冲区中的内容：");
		
		while(read.hasRemaining()) {
			
			int x=read.get();
			System.out.println( x+"、");
			
			
		}
		System.out.println();
		
		read.put(30);
		
	}

//创建直接缓冲区
	public static void method4() {
		
		ByteBuffer buf=null;
		
		buf=ByteBuffer.allocateDirect(10);
		
		byte temp[]= {1,3,5,7,9};
		
		buf.put(temp);
		
		buf.flip();
		
		System.out.println("缓冲区中的内容:");
		
		while(buf.hasRemaining()) {
			
			int x=buf.get();
			
			System.out.println(x+"、");
			
		}
		
	}

//使用输出通道输出内容	
	public static void channelMethod1() throws IOException {
		
		String info[]= {"MLDN","JAVA","www.mldn.cn","www.mldnjava.cn","lxh","lxh"};
		
		File file=new File("e:"+File.separator+"out.txt");
		
		FileOutputStream output=null;
		output=new FileOutputStream(file);
		
		FileChannel fout=null;
		fout=output.getChannel();
		
		ByteBuffer buf=ByteBuffer.allocate(1024);
		
		for(int i=0;i<info.length;i++) {
			
			buf.put(info[i].getBytes());
			
		}
		
		buf.flip();
		fout.write(buf);
		fout.close();
		output.close();
		
		System.out.println("OK!");
	}


	public static void channelMethod2()throws IOException {
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
