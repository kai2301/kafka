package User.model;

public class Role {
	int RoleID;
	String Name;
	public int getRoleID() {
		return RoleID;
	}
	public void setRoleID(int roleID) {
		RoleID = roleID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Role(int roleID, String name) {
		super();
		RoleID = roleID;
		Name = name;
	}
}
