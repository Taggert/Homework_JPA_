package com.company.service.menu;

import org.springframework.stereotype.Component;

@Component
public abstract class Item {
	
	protected InputOutput inputOutput;

	public Item() {
	}

	public Item(InputOutput inputOutput) {
		this.inputOutput = inputOutput;
	}
	
	public abstract String displayedName();
	
	public abstract void perform();
	
	public boolean isExit(){
		return false;
	}

}
