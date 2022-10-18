package User.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "NEWS")
public class News {
	@Id
	private String ID;
	private String Creator_ID;
	private String Title;
	private String Content;
	private LocalDate Publish;
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
	public News(String iD, String creator_ID, String title, String content, LocalDate publish) {
		super();
		ID = iD;
		Creator_ID = creator_ID;
		Title = title;
		Content = content;
		Publish = publish;
	}
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
