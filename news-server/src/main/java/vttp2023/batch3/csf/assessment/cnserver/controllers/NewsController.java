package vttp2023.batch3.csf.assessment.cnserver.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2023.batch3.csf.assessment.cnserver.models.TagCount;
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
			String objectId = nService.postNews(jo);
			JsonObject outgoingJo;
			if (objectId == null) {
				outgoingJo = Json.createObjectBuilder()
					.add("error", "There was an error posting your article")
					.build();
			}
			else {
				outgoingJo = Json.createObjectBuilder()
								.add("newsId", objectId)
								.build();
			}
			 
		return ResponseEntity.ok(outgoingJo.toString());
	}

	// TODO: Task 2
	@GetMapping(path="/gettagswithcount")
	public ResponseEntity<String> getTagsWithCount(@RequestParam String minute) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		List<TagCount> tags = nService.getTags(minute);
		List<JsonObject> jsonList = new ArrayList<>();
		for(TagCount tag: tags) {
			JsonObject outgoingJo = Json.createObjectBuilder()
						.add("tag", tag.getClass().getField("tag").get(tag).toString())
						.add("count", tag.getClass().getField("count").get(tag).toString())
						.build();
			jsonList.add(outgoingJo);
		}
		return ResponseEntity.ok(jsonList.toString());
	}

	// TODO: Task 3

}
