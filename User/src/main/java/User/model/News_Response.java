package User.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class News_Response {
	private String ID;
	private String Creator_ID;
	private String Title;
	private String Content;
	private LocalDate Publish;
	private String Creator_Name;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getCreator_ID() {
		return Creator_ID;
	}
	public void setCreator_ID(String creator_ID) {
		Creator_ID = creator_ID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public LocalDate getPublish() {
		return Publish;
	}
	public void setPublish(LocalDate publish) {
		Publish = publish;
	}
	public News_Response(String iD, String creator_ID, String title, String content, LocalDate publish) {
		super();
		ID = iD;
		Creator_ID = creator_ID;
		Title = title;
		Content = content;
		Publish = publish;
	}
	
	public News_Response(News input, User creator) {
		super();
		ID = input.getID();
		Creator_ID = input.getCreator_ID();
		Title = input.getTitle();
		Content = input.getContent();
		Publish = input.getPublish();
		Creator_Name = creator.getHoTen();
	}
	
	public News_Response() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCreator_Name() {
		return Creator_Name;
	}
	public void setCreator_Name(String creator_Name) {
		Creator_Name = creator_Name;
	}
	
	
	
}
