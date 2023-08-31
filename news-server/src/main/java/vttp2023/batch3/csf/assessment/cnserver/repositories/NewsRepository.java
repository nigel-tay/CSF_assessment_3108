package vttp2023.batch3.csf.assessment.cnserver.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.mongodb.internal.operation.CountOperation;

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

	/**
	 * db.getCollection('news').aggregate(
		[
			{
			$match: {
				'postDate.num': {
				$lt: 1693465573419,
				$gt: 1693461146434
				}
			}
			},
			{ $unwind: { path: '$tags' } },
			{
			$group: {
				_id: '$tags.value',
				count: { $sum: 1 }
			}
			},
			{ $sort: { count: -1 } }
		]
		);
	 */

	public List<Document> getTagsWithCount(Long now, Long delimiter) {
		Criteria criteria = Criteria.where("postDate.num").lt(now).gt(delimiter);
		MatchOperation mo = Aggregation.match(criteria);
		UnwindOperation uo = Aggregation.unwind("tags");
		GroupOperation go = Aggregation.group()
										.first("_id").as("tag")
										.count().as("count");
		SortOperation so = Aggregation.sort(Direction.ASC, "count");
		Aggregation pipeline = Aggregation.newAggregation(mo, uo, go, so);
		return mTemplate.aggregate(pipeline, "news", Document.class).getMappedResults();
	}

	// TODO: Task 3
	// Write the native Mongo query in the comment above the method


}
