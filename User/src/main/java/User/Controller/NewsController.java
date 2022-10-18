package User.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import User.model.User;
import User.model.ApiResponse;
import User.model.LoginResponse;
import User.model.ThamGiaDuAn;
import User.model.TokenResponse;
import User.model.News;
import User.model.News_Response;
import User.model.List_Staff;
import User.repository.NewRepository;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class NewsController {
	@Autowired
	NewRepository repoNew;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoOperations mongoOperation;
	
	@GetMapping("/get_news")
	public ResponseEntity<ApiResponse<List<News_Response>>> get_news(@RequestParam(value = "date") String date_input) {
		try {
			List<News> wfhlst = new ArrayList<News>();
			List<News_Response> result = new ArrayList<News_Response>();
			if (!(date_input != null) || date_input.equals("")) {
//				repoNew.findAll().forEach(wfhlst::add);
				repoNew.findAll(Sort.by(Sort.Direction.DESC, "Publish")).forEach(wfhlst::add);
				if (wfhlst.isEmpty()) {
					ApiResponse<List<News_Response>> resp = new ApiResponse<>(0,"Not found any news",result);
					return new ResponseEntity<>(resp, HttpStatus.CREATED);
				}
				for (News i : wfhlst) {
					Query q1 = new Query();
					q1.addCriteria(Criteria.where("ID").is(i.getCreator_ID()));
					User nhanvien = mongoTemplate.findOne(q1, User.class);
					News_Response temp = new News_Response(i, nhanvien);
					result.add(temp);
				}
				ApiResponse<List<News_Response>> resp = new ApiResponse<>(0,"Success",result);
				return new ResponseEntity<>(resp, HttpStatus.CREATED);
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//			LocalDate dt = dtf.parseLocalDate();
			LocalDate date_result = LocalDate.parse(date_input, dtf);
			Query q = new Query();
			q.addCriteria(Criteria.where("Publish").is(date_result));
			wfhlst = mongoTemplate.find(q, News.class);
//			checklst = mongoTemplate.find(q, Check_in_out.class);
			Collections.sort(wfhlst, Comparator.comparing(News::getPublish).reversed());
			if(wfhlst.isEmpty()) {
				ApiResponse<List<News_Response>> resp = new ApiResponse<>(0,"Not found news in this date",result);
				return new ResponseEntity<>(resp, HttpStatus.CREATED);
			}
			for (News i : wfhlst) {
				Query q1 = new Query();
				q1.addCriteria(Criteria.where("ID").is(i.getCreator_ID()));
				User nhanvien = mongoTemplate.findOne(q1, User.class);
				News_Response temp = new News_Response(i, nhanvien);
				result.add(temp);
			}
			ApiResponse<List<News_Response>> resp = new ApiResponse<>(0,"Success",result);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		} catch (Exception e) {
			ApiResponse<List<News_Response>> resp = new ApiResponse<>(1,"Invalid date input",null);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		}
	}
	
	@PostMapping("/add_news")
	public ResponseEntity<ApiResponse<News>> AddNews(@RequestBody News input) {
		try {
			input.setID(UUID.randomUUID().toString());
			News _new = repoNew.save(new News(input.getID(), input.getCreator_ID(), input.getTitle(), input.getContent(), LocalDate.now()));
			ApiResponse<News> resp = new ApiResponse<>(0, "Success", _new);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		} catch (Exception e) {
			ApiResponse<News> resp = new ApiResponse<>(1,"Internal error",null);
			return new ResponseEntity<>(resp, HttpStatus.CREATED);
		}
	}
}