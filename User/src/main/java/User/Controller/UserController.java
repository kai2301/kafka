package User.Controller;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import User.model.User;
import User.model.ApiResponse;
import User.model.LoginResponse;
import User.model.ThamGiaDuAn;
import User.model.TokenResponse;
import User.model.UpdatePass;
import User.model.List_Staff;
import User.repository.UserRepository;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	UserRepository repoNV;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoOperations mongoOperation;
	
	@GetMapping("/view_staff_list")
	public ResponseEntity<ApiResponse<List<LoginResponse>>> View_staff_list() {
		try {
			List<User> nhanvienlst = new ArrayList<User>();
			List<LoginResponse> result = new ArrayList<LoginResponse>();

			repoNV.findAll().forEach(nhanvienlst::add);
			
			for(User i : nhanvienlst) {
				String uri = "https://gatewayteam07.herokuapp.com/api/get_manager1_of_staff/" + i.getID();
				RestTemplate restTemplate = new RestTemplate();
				ThamGiaDuAn result1 = restTemplate.getForObject(uri, ThamGiaDuAn.class);
				
				if(result1.getID() == null) {
					String uri1 = "https://gatewayteam07.herokuapp.com/api/staff_nghiphep/53d9cb62-e667-43a5-b1fd-dc2cf6d04419";
					RestTemplate restTemplate1 = new RestTemplate();
					User staff = restTemplate1.getForObject(uri1, User.class);
					LoginResponse result2 = new LoginResponse(i, staff);
					result.add(result2);
				}
				else {
					try {
						Query q1 = new Query();
						q1.addCriteria(Criteria.where("ID").is(result1.getMaTL()));
						User nhanvien = mongoTemplate.findOne(q1, User.class);
						
						LoginResponse result3 = new LoginResponse(i, nhanvien);
						result.add(result3);
					}catch (Exception e) {
						ApiResponse<List<LoginResponse>> resp = new ApiResponse<>(1,"Can't found manager from microservice DuAn", null);
						return new ResponseEntity<>(resp, HttpStatus.CREATED);
					}
				}
			}
			
			if (result.isEmpty()) {
				ApiResponse<List<LoginResponse>> resp = new ApiResponse<List<LoginResponse>>(1,"Failure",null);
				return new ResponseEntity<>(resp, HttpStatus.OK);
			}

//			String uri = "http://localhost:8081/api/get_manager1_of_staff/ecf9d133-a519-4c0c-938b-96c0851d824a";
//			RestTemplate restTemplate = new RestTemplate();
//			ThamGiaDuAn result1 = restTemplate.getForObject(uri, ThamGiaDuAn.class);
//
//			System.out.println(result1.getID());
			
			ApiResponse<List<LoginResponse>> resp = new ApiResponse<List<LoginResponse>>(0,"Success",result);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception e) {
			ApiResponse<List<LoginResponse>> resp = new ApiResponse<List<LoginResponse>>(1,"Failure",null);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/view_staff_by_id/{id_staff}")
	public ResponseEntity<ApiResponse<LoginResponse>> View_staff_by_id(@PathVariable(value = "id_staff") String id_staff) {
		try {	
			List<User> nhanvienlst = new ArrayList<User>();
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(id_staff));
			nhanvienlst = mongoTemplate.find(q, User.class);
			if (!nhanvienlst.isEmpty()) {
				for (User i : nhanvienlst) {
					String uri = "https://gatewayteam07.herokuapp.com/api/get_manager1_of_staff/" + i.getID();
					RestTemplate restTemplate = new RestTemplate();
					ThamGiaDuAn result1 = restTemplate.getForObject(uri, ThamGiaDuAn.class);
					if(result1.getID() == null) {
						String uri1 = "https://gatewayteam07.herokuapp.com/api/staff_nghiphep/53d9cb62-e667-43a5-b1fd-dc2cf6d04419";
						RestTemplate restTemplate1 = new RestTemplate();
						User staff = restTemplate1.getForObject(uri1, User.class);
						LoginResponse result2 = new LoginResponse(i, staff);
						ApiResponse<LoginResponse> resp = new ApiResponse<LoginResponse>(0,"Success",result2);
						return new ResponseEntity<>(resp, HttpStatus.OK);
					}
					else {
						try {
							Query q1 = new Query();
							q1.addCriteria(Criteria.where("ID").is(result1.getMaTL()));
							User nhanvien = mongoTemplate.findOne(q1, User.class);
							LoginResponse result3 = new LoginResponse(i, nhanvien);
							ApiResponse<LoginResponse> resp = new ApiResponse<LoginResponse>(0,"Success",result3);
							return new ResponseEntity<>(resp, HttpStatus.OK);
						}catch (Exception e) {
							ApiResponse<LoginResponse> resp = new ApiResponse<>(1,"Can't found manager from microservice DuAn", null);
							return new ResponseEntity<>(resp, HttpStatus.CREATED);
						}
					}
				}
			}
			ApiResponse<LoginResponse> resp = new ApiResponse<>(1,"Can't found staff", null);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		} catch (Exception e) {
			ApiResponse<LoginResponse> resp = new ApiResponse<LoginResponse>(1,"Failure",null);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
	}
	
	@GetMapping("/staff_nghiphep/{id_staff}")
	public ResponseEntity<User> staff_nghiphep(@PathVariable(value = "id_staff") String id_staff) {
		try {	
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(id_staff));
			User nhanvien = mongoTemplate.findOne(q, User.class);
			return new ResponseEntity<>(nhanvien, HttpStatus.OK);
		} catch (Exception e) {
			User error = new User();
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/update_sophepconlai/{id_staff}")
	public ResponseEntity<ApiResponse<User>> Update_sophepconlai(@PathVariable(value = "id_staff") String id_staff) {
		try {	
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(id_staff));
			User nhanvien = mongoTemplate.findOne(q, User.class);
			
			if(nhanvien.getID() != "") {
				System.out.println(id_staff);
				System.out.println(nhanvien.getSoPhepConLai() - 1);
				nhanvien.setSoPhepConLai(nhanvien.getSoPhepConLai() - 1);	

				ApiResponse<User> resp = new ApiResponse<User>(0, "Update Successfully", repoNV.save(nhanvien));
				return new ResponseEntity<>(resp, HttpStatus.CREATED);
			}
			ApiResponse<User> resp = new ApiResponse<User>(1, "Staff ID is wrong!", null);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		} catch (Exception e) {
			ApiResponse<User> error = new ApiResponse<User>(1, "invalid or miss input", null);
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/create_staff")
	public ResponseEntity<ApiResponse<LoginResponse>> Create_staff(@RequestBody User nv) {
		try {
			if(nv.getChucVu() <0 || nv.getChucVu()>5) {
				ApiResponse<LoginResponse> resp = new ApiResponse<>(1,"invalid role",null);
				return new ResponseEntity<>(resp, HttpStatus.CREATED);
			}
			if ((!nv.getUserName().endsWith("@udpt.group7.com") || (nv.getPassWord().length() < 5))) {
				ApiResponse<LoginResponse> resp = new ApiResponse<>(1,"invalid username or password length",null);
				return new ResponseEntity<>(resp, HttpStatus.CREATED);
			}
			List<User> user = new ArrayList<User>();
			Query q = new Query();
			q.addCriteria(Criteria.where("UserName").is(nv.getUserName()));
			user = mongoTemplate.find(q, User.class);
			if(!user.isEmpty()) {
				ApiResponse<LoginResponse> resp = new ApiResponse<>(1,"Already registered",null);
				return new ResponseEntity<>(resp, HttpStatus.CREATED);
			}
			
			if(nv.getAvatar() == null || nv.getAvatar() == "") {
				if (nv.getGioiTinh().equals("Nam")) {
					nv.setAvatar("https://firebasestorage.googleapis.com/v0/b/udpt-fe-fe54d.appspot.com/o/default_avatar%2Fman.jpeg?alt=media&token=8b50f604-ce78-42f9-9002-7c9abb049100");
				}
				else {
					nv.setAvatar("https://firebasestorage.googleapis.com/v0/b/udpt-fe-fe54d.appspot.com/o/default_avatar%2Fgirl.jpg?alt=media&token=519192b9-7d62-4db6-8065-8e3953315949");
				}
			}
			
			nv.setID(UUID.randomUUID().toString());
			User _nv = repoNV.save(new User(nv.getID(), nv.getHoTen(), nv.getUserName(), givenPassword_whenHashing_thenVerifying(nv.getPassWord()), nv.getGioiTinh(), nv.getChucVu(),
					nv.getDiaChi(), nv.getEmail(), nv.getSDT(), nv.getAvatar()));
			LoginResponse result = new LoginResponse(_nv);
			ApiResponse<LoginResponse> resp = new ApiResponse<LoginResponse>(0, "Success", result);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);

//			ApiResponse<NhanVien> resp = new ApiResponse<NhanVien>(0, "Can't request", null);
//			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		} catch (Exception e) {
			ApiResponse<LoginResponse> resp = new ApiResponse<LoginResponse>(1,"Failure",null);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody User user) {
		try {
			Query q = new Query();
			q.addCriteria(Criteria.where("UserName").is(user.getUserName()).and("PassWord").is(givenPassword_whenHashing_thenVerifying(user.getPassWord())));
			List<User> check = mongoTemplate.find(q, User.class);
			if(check.isEmpty()) {
				ApiResponse<TokenResponse> resp = new ApiResponse<>(1,"Wrong username or password", null);
				return new ResponseEntity<>(resp, HttpStatus.CREATED);
			}
			for (User i : check) {
				LoginResponse login = new LoginResponse(i);
				TokenResponse result = new TokenResponse("", login);
				ApiResponse<TokenResponse> resp = new ApiResponse<TokenResponse>(0,"Success",result);
				return new ResponseEntity<>(resp, HttpStatus.OK);
			}
			ApiResponse<TokenResponse> resp = new ApiResponse<>(1,"Wrong username or password", null);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		} catch (Exception e) {
			ApiResponse<TokenResponse> resp = new ApiResponse<TokenResponse>(1,"Failure",null);
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
	}

	@PutMapping("/update_profile_staff")
	public ResponseEntity<ApiResponse<LoginResponse>> Update_profile_staff(@RequestBody User user 
			
//			@RequestParam(value = "id_staff") String id_staff, @RequestParam(value = "avatar") String avatar,
//			@RequestParam(value = "diachi") String diachi, @RequestParam(value = "email") String email, @RequestParam(value = "sdt") String sdt
			) {
		try {						
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(user.getID()));
			User wfh = mongoTemplate.findOne(q, User.class);
			if(wfh.getID() != "") {
				wfh.setDiaChi(user.getDiaChi());	
				wfh.setEmail(user.getEmail());
				wfh.setSDT(user.getSDT());
				wfh.setAvatar(user.getAvatar());
				repoNV.save(wfh);
				LoginResponse login = new LoginResponse(wfh);
				ApiResponse<LoginResponse> resp = new ApiResponse<LoginResponse>(0,"Success",login);
				return new ResponseEntity<>(resp, HttpStatus.CREATED);
			}
			ApiResponse<LoginResponse> resp = new ApiResponse<>(1,"invalid or miss input", null);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
			
		} catch (Exception e) {
			ApiResponse<LoginResponse> resp = new ApiResponse<>(1,"invalid or miss input", null);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		}
	}
	
	@PutMapping("/update_pwd")
	public ResponseEntity<ApiResponse<LoginResponse>> update_pwd(@RequestBody UpdatePass input 
			
//			@RequestParam(value = "id_staff") String id_staff, @RequestParam(value = "avatar") String avatar,
//			@RequestParam(value = "diachi") String diachi, @RequestParam(value = "email") String email, @RequestParam(value = "sdt") String sdt
			) {
		try {						
			Query q = new Query();
			q.addCriteria(Criteria.where("ID").is(input.getId()).and("PassWord").is(givenPassword_whenHashing_thenVerifying(input.getOldPwd())));
			User wfh = mongoTemplate.findOne(q, User.class);
			if(wfh.getID() != "") {
				if (input.getNewPwd().length() < 5) {
					ApiResponse<LoginResponse> resp = new ApiResponse<LoginResponse>(1, "new pass length invalid!", null);
					return new ResponseEntity<>(resp, HttpStatus.CREATED);
				}
				wfh.setPassWord(givenPassword_whenHashing_thenVerifying(input.getNewPwd()));
				repoNV.save(wfh);
				LoginResponse login = new LoginResponse(wfh);
				ApiResponse<LoginResponse> resp = new ApiResponse<LoginResponse>(0,"Success",login);
				return new ResponseEntity<>(resp, HttpStatus.CREATED);
			}
			ApiResponse<LoginResponse> resp = new ApiResponse<LoginResponse>(1, "Staff ID is wrong!", null);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
			
		} catch (Exception e) {
			ApiResponse<LoginResponse> resp = new ApiResponse<LoginResponse>(1, "Not existing data", null);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		}
	}
	
	public String givenPassword_whenHashing_thenVerifying(String Password) 
	  throws NoSuchAlgorithmException {
//	    String hash = "35454B055CC325EA1AF2126E27707052";
//	    String password = "ILoveJava";
	        
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(Password.getBytes());
	    byte[] digest = md.digest();
	    String myHash = DatatypeConverter
	      .printHexBinary(digest).toUpperCase();
	        
	    return myHash;
	}
	
	//Return list staff of base manager
	@GetMapping("/staff_basemanager")
	public ResponseEntity<List_Staff> staff_basemanager() {
		try {
			List<User> nhanvienlst = new ArrayList<User>();
//			List<LoginResponse> result = new ArrayList<LoginResponse>();
			List<String> result = new ArrayList<String>();

			repoNV.findAll().forEach(nhanvienlst::add);
			
			for(User i : nhanvienlst) {
				String uri = "https://gatewayteam07.herokuapp.com/api/get_manager1_of_staff/" + i.getID();
				RestTemplate restTemplate = new RestTemplate();
				ThamGiaDuAn result1 = restTemplate.getForObject(uri, ThamGiaDuAn.class);
				
				if(result1.getID() == null) {
					result.add(i.getID());
				}
//				else {
//					try {
//						Query q1 = new Query();
//						q1.addCriteria(Criteria.where("ID").is(result1.getMaTL()));
//						User nhanvien = mongoTemplate.findOne(q1, User.class);
//						
//						LoginResponse result3 = new LoginResponse(i, nhanvien);
//						result.add(result3);
//					}catch (Exception e) {
//						ApiResponse<List<LoginResponse>> resp = new ApiResponse<>(1,"Can't found manager from microservice DuAn", null);
//						return new ResponseEntity<>(resp, HttpStatus.CREATED);
//					}
//				}
			}
			
//			if (result.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}

			
//			ApiResponse<List<LoginResponse>> resp = new ApiResponse<List<LoginResponse>>(0,"Success",result);
			List_Staff response = new List_Staff(result);
			// System.out.println(tgda.getID());
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}