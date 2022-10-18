package User.model;

import java.util.List;

public class LoginResponse {
	private String ID;
	private String HoTen;
	private String GioiTinh;
	private Role ChucVu;
	private String DiaChi;
	private String Email;
	private String SDT;
	private int SoPhepConLai;
	private int TrangThaiLamViec;
	private String Avatar;
	private String EmailCongTy;
	private String BaseManager;
	private LoginResponse Manager_info;
	String[] ListRole = {"Staff", "IT Staff", "Project Manager", "Accountant", "Team Leader", "Director"};
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public Role getChucVu() {
		return ChucVu;
	}
	public void setChucVu(Role chucVu) {
		ChucVu = chucVu;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public int getSoPhepConLai() {
		return SoPhepConLai;
	}
	public void setSoPhepConLai(int soPhepConLai) {
		SoPhepConLai = soPhepConLai;
	}
	public int getTrangThaiLamViec() {
		return TrangThaiLamViec;
	}
	public void setTrangThaiLamViec(int trangThaiLamViec) {
		TrangThaiLamViec = trangThaiLamViec;
	}
	public String getAvatar() {
		return Avatar;
	}
	public void setAvatar(String avatar) {
		Avatar = avatar;
	}
//	public LoginResponse(String iD, String hoTen, String gioiTinh, int chucVu, String diaChi, String email,
//			String sDT, int soPhepConLai, int trangThaiLamViec) {
//		super();
//		ID = iD;
//		HoTen = hoTen;
//		GioiTinh = gioiTinh;
//		ChucVu.RoleID = chucVu;
//		ChucVu.Name = ListRole[chucVu];
//		DiaChi = diaChi;
//		Email = email;
//		SDT = sDT;
//		SoPhepConLai = soPhepConLai;
//		TrangThaiLamViec = trangThaiLamViec;
//	}
	
	public LoginResponse(User input) {
		super();
		ID = input.getID();
		HoTen = input.getHoTen();
		GioiTinh = input.getGioiTinh();
		ChucVu = new Role(input.getChucVu(), ListRole[input.getChucVu()]);
		DiaChi = input.getDiaChi();
		Email = input.getEmail();
		SDT = input.getSDT();
		SoPhepConLai = input.getSoPhepConLai();
		TrangThaiLamViec = input.getTrangThaiLamViec();
		Avatar = input.getAvatar();
		EmailCongTy = input.getUserName();
		BaseManager = "53d9cb62-e667-43a5-b1fd-dc2cf6d04419";
	}
	
	public LoginResponse(User staff, User manager) {
		super();
		ID = staff.getID();
		HoTen = staff.getHoTen();
		GioiTinh = staff.getGioiTinh();
		ChucVu = new Role(staff.getChucVu(), ListRole[staff.getChucVu()]);
		DiaChi = staff.getDiaChi();
		Email = staff.getEmail();
		SDT = staff.getSDT();
		SoPhepConLai = staff.getSoPhepConLai();
		TrangThaiLamViec = staff.getTrangThaiLamViec();
		Avatar = staff.getAvatar();
		EmailCongTy = staff.getUserName();
		BaseManager = "53d9cb62-e667-43a5-b1fd-dc2cf6d04419";
		Manager_info = new LoginResponse(manager);
	}
	
	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginResponse getManager_info() {
		return Manager_info;
	}
	public void setManager_info(LoginResponse manager_info) {
		Manager_info = manager_info;
	}
	public String getEmailCongTy() {
		return EmailCongTy;
	}
	public void setEmailCongTy(String emailCongTy) {
		EmailCongTy = emailCongTy;
	}
	public String getBaseManager() {
		return BaseManager;
	}
	public void setBaseManager(String baseManager) {
		BaseManager = baseManager;
	}
	
	
}
