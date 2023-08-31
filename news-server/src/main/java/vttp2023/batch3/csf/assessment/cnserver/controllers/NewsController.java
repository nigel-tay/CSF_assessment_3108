package vttp2023.batch3.csf.assessment.cnserver.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class NewsController {

	// TODO: Task 1
	@PostMapping(path="/receiveform", consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postFormToMongo(
		@RequestPart String title,
		@RequestPart MultipartFile photo,
		@RequestPart String description,
		@RequestPart String tags
		) {
			System.out.println(title.toString() + "  " + photo.toString() + "  " + description.toString() + "  " + tags.toString());
		return null;
	}

	// TODO: Task 2


	// TODO: Task 3

}
