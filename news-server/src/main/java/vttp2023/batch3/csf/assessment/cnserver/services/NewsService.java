package vttp2023.batch3.csf.assessment.cnserver.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.JsonObject;
import vttp2023.batch3.csf.assessment.cnserver.models.News;
import vttp2023.batch3.csf.assessment.cnserver.models.TagCount;
import vttp2023.batch3.csf.assessment.cnserver.repositories.ImageRepository;
import vttp2023.batch3.csf.assessment.cnserver.repositories.NewsRepository;

@Service
public class NewsService {

	@Autowired
	private ImageRepository iRepo;

	@Autowired
	private NewsRepository nRepo;
	
	// TODO: Task 1
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns the news id
	public String postNews(JsonObject jo) {
		Document articleDoc = new Document()
                                    .append("postDate", jo.getJsonNumber("postDate"))
                                    .append("title", jo.getJsonString("title"))
                                    .append("description", jo.getJsonString("description"))
                                    .append("image", jo.getJsonString("image"))
                                    .append("tags", jo.getJsonArray("tags"));
		return nRepo.postToMongo(articleDoc);
	}

	public String saveImageToS3(MultipartFile mf) throws IOException {
		return iRepo.uploadFile(mf);
	}
	 
	// TODO: Task 2
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns a list of tags and their associated count
	public List<TagCount> getTags(String minute) {
		Long longMinute = Long.parseLong(Integer.toString((Integer.parseInt(minute) * 60 * 1000)));
		Long now = new Date().getTime();
		Long delimiter = new Date().getTime() - longMinute;

		List<TagCount> ll = new LinkedList<>();
		nRepo.getTagsWithCount(now, delimiter)
				.stream()
				.forEach(d -> {
						TagCount tc = new TagCount(d.getString(ll), d.getInteger("count"));
						ll.add(tc);
					}
				);
		return ll;
	}

	// TODO: Task 3
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns a list of news
	public List<News> getNewsByTag(/* Any number of parameters */) {
		return new LinkedList<>();
	}
	
}
