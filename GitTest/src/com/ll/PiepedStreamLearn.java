package com.ll;

import java.io.*;

class Send implements Runnable {

	private PipedOutputStream pios = null;

	public Send() {

		pios = new PipedOutputStream();
	}

	@Override
	public void run() {

		try {
			pios.write("Hello LLLL".getBytes());

		} catch (IOException e) {

			e.printStackTrace();
		} finally {

			try {
				this.pios.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public PipedOutputStream getOutputPieped() {

		return pios;
	}

}

class Recieve implements Runnable {

	private PipedInputStream pins = null;

	public Recieve() {

		pins = new PipedInputStream();
	}

	public PipedInputStream recieve() {

		return pins;
	}

	@Override
	public void run() {
		byte[] b = new byte[1024];

		int n = 0;

		try {
			while ((n = pins.read(b)) != -1) {

				System.out.println(new String(b, 0, n));
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {

			try {
				this.pins.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

}

public class PiepedStreamLearn {

	public static void main(String[] args) throws IOException {
		Send send=new Send();
		Recieve reciv=new Recieve();
		
		send.getOutputPieped().connect(reciv.recieve());
		
		new Thread(send).start();
		new Thread(reciv).start();

	}

}
