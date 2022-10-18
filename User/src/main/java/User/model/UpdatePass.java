package User.model;

public class UpdatePass {
	private String id;
	private String OldPwd;
	private String NewPwd;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOldPwd() {
		return OldPwd;
	}
	public void setOldPwd(String oldPwd) {
		OldPwd = oldPwd;
	}
	public String getNewPwd() {
		return NewPwd;
	}
	public void setNewPwd(String newPwd) {
		NewPwd = newPwd;
	}
	public UpdatePass(String id, String oldPwd, String newPwd) {
		super();
		this.id = id;
		OldPwd = oldPwd;
		NewPwd = newPwd;
	}
	public UpdatePass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
