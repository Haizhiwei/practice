package com.hai.interview.design_mode.template;

public class StringDisplay extends AbstractDisplay{
	private String str;
	private int width;
	public StringDisplay(String str ) {
		this.str = str ;
		this.width = str.getBytes().length;
		//this.width = str.length();
		System.out.println(width);
	}

	@Override
	public void open() {
		printLine();
		
	}

	@Override
	public void print() {
		System.out.println("|"+str+"|");
		
	}

	@Override
	public void close() {
		printLine();
		
	}
	private void printLine() {
		System.out.print("+");
		for(int i=0;i<width;i++) {
			System.out.print("-");
		}
		System.out.println("+");
	}

}
