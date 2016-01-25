package com.epam;

public enum Rights {
	ADMIN(1), USER(2);
	
	private int num;
	
	public int getNum() {
		return num;
	}

	private Rights(int number) {
		num = number;
	}
	
}
