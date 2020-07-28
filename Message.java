package main;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 42L;
	//Test
	//Test 2
	// test 3
	private long id;
	private String content;
	
	public Message(long id, String content) {
		this.id = id;
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public long getId() {
		return id;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Message ID: " + id + " Content: " + content;
	}
}
