package com.alekapps.messagingv2;

public class Item {
	private String message;
    private String extra;
 
    public Item(String message, String extra) {
        super();
        this.message = message;
        this.extra = extra;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
    
    
}
