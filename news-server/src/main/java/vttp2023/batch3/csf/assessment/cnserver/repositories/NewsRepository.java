package vttp2023.batch3.csf.assessment.cnserver.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepository {

	@Autowired
	private MongoTemplate mTemplate;

	// TODO: Task 1 
	// Write the native Mongo query in the comment above the method

	/**
		db.news.insert({
			postDate: 1234,
			title: "",
			description: "",
			image: "",
			tags: ["", ""]
		})
	*/

	public String postToMongo(Document articleDoc) {
		Document newDoc = mTemplate.insert(articleDoc, "news");
		return newDoc.getObjectId("_id").toString();
	}

	// TODO: Task 2 
	// Write the native Mongo query in the comment above the method


	// TODO: Task 3
	// Write the native Mongo query in the comment above the method


}
