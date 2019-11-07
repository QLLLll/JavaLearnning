package com.ll;
import java.io.*;


public class MyCopy {

	@SuppressWarnings("resource")
	public static void main(String[] args)throws IOException {

	 BufferedInputStream bis=null;
	 
	 BufferedOutputStream bos=null;
	 
	 try {
		 
		 bis=new BufferedInputStream(new FileInputStream("c:\\1.txt"));
		 
		 bos=new BufferedOutputStream(new FileOutputStream("d:\\copyed.txt"));
		 
		 byte[]buffer=new byte[1024];
		 
		 int len=0;
		 
		 while((len=bis.read(buffer))!=-1) {
			 
			 bos.write(buffer, 0, len);
			 
		 }
		 
		 System.out.println("finished");
		 
		 
	 }catch(Exception e) {
		 throw e;
	 }
	 finally {
		 if(bis!=null) {
			 
			 try {
				 bis.close();
			 }
			 catch(Exception e) {
				 
				 throw e; 
			 }
		 }
		 if(bos!=null) {
			 
			 try {
				 bos.close();
			 }
			 catch(Exception e) {throw e;}
			 
		 }
	 		}				 
		}
	}