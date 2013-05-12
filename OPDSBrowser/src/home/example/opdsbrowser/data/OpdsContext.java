package home.example.opdsbrowser.data;

import android.graphics.Bitmap;

public class OpdsContext {
	
	private static OpdsContext inst = new OpdsContext();
	
	private OpdsContext(){}
	
	public static OpdsContext getContext(){
		return inst;
	}
	
	private String prev;
	
	private String next;
	
	private String icon;
	
	private Bitmap image;
	
	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

}
