package Request.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.annotation.PostConstruct;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Document(collection = "YEUCAUTHIETBI")

public class YeuCauThietBi_Response {

	@Id
	private String ID;
	private String MaNhanVien;
	private String TenNhanVien;
	private String MoTa;
	private float ChiPhi;
	private float SoLuong;
	private String MaNguoiDuyet;
	private String LyDoTuChoi;
	private int TrangThai;
	
	

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMaNhanVien() {
		return MaNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		MaNhanVien = maNhanVien;
	}

	
	public String getTenNhanVien() {
		return TenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		TenNhanVien = tenNhanVien;
	}

	public String getMoTa() {
		return MoTa;
	}

	public void setMoTa(String moTa) {
		MoTa = moTa;
	}

	public float getChiPhi() {
		return ChiPhi;
	}

	public void setChiPhi(float chiPhi) {
		ChiPhi = chiPhi;
	}

	public float getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(float soLuong) {
		SoLuong = soLuong;
	}

	public String getMaNguoiDuyet() {
		return MaNguoiDuyet;
	}

	public void setMaNguoiDuyet(String maNguoiDuyet) {
		MaNguoiDuyet = maNguoiDuyet;
	}

	public String getLyDoTuChoi() {
		return LyDoTuChoi;
	}

	public void setLyDoTuChoi(String lyDoTuChoi) {
		LyDoTuChoi = lyDoTuChoi;
	}

	public int getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}
	
	

	

	public YeuCauThietBi_Response(String iD, String maNhanVien, String tenNhanVien, String moTa, float chiPhi,
			float soLuong, String maNguoiDuyet, String lyDoTuChoi, int trangThai) {
		super();
		ID = iD;
		MaNhanVien = maNhanVien;
		TenNhanVien = tenNhanVien;
		MoTa = moTa;
		ChiPhi = chiPhi;
		SoLuong = soLuong;
		MaNguoiDuyet = maNguoiDuyet;
		LyDoTuChoi = lyDoTuChoi;
		TrangThai = trangThai;
	}

	public YeuCauThietBi_Response(YeuCauThietBi request, User NhanVien) {
		super();
		ID = request.getID();
		MaNguoiDuyet = request.getMaNguoiDuyet();
		MaNhanVien = request.getMaNhanVien();
		TenNhanVien = NhanVien.getHoTen();
		MoTa = request.getMoTa();
		ChiPhi = request.getChiPhi();
		SoLuong = request.getSoLuong();
		MaNguoiDuyet = request.getMaNguoiDuyet();
		LyDoTuChoi = request.getLyDoTuChoi();
		TrangThai = request.getTrangThai();
		
	}

	public YeuCauThietBi_Response() {
		super();
		// TODO Auto-generated constructor stub
	}

}
