package vttp2023.batch3.csf.assessment.cnserver.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2023.batch3.csf.assessment.cnserver.models.News;
import vttp2023.batch3.csf.assessment.cnserver.services.NewsService;

@RestController
@CrossOrigin
public class NewsController {

	@Autowired
	private NewsService nService;

	// TODO: Task 1
	@PostMapping(path="/receiveform", consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postFormToMongo(
		@RequestPart String title,
		@RequestPart MultipartFile photo,
		@RequestPart String description,
		@RequestPart String tags
		) throws IOException {
			System.out.println(title.toString() + "  " + photo.toString() + "  " + description.toString() + "  " + tags.toString());
			
			String uuid = nService.saveImageToS3(photo);

			Date date = new Date();
			List<String> tagsList = Arrays.asList(tags.split(",", -1));

			JsonObject jo = Json.createObjectBuilder()
								.add("postDate", date.getTime())
								.add("title", title.toString())
								.add("description", description.toString())
								.add("image", "https://fredbarney.sgp1.cdn.digitaloceanspaces.com/"+uuid)
								.add("tags", Json.createArrayBuilder(tagsList))
								.build();

		return ResponseEntity.ok(jo.toString());
	}

	// TODO: Task 2


	// TODO: Task 3

}
