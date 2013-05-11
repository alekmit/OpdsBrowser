package home.example.opdsbrowser.data;

public class OpdsContext {
	
	private static OpdsContext inst = new OpdsContext();
	
	private OpdsContext(){}
	
	public static OpdsContext getContext(){
		return inst;
	}
	
	private String prev;
	
	private String next;
	
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

}
