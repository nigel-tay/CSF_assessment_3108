package vttp2023.batch3.csf.assessment.cnserver.repositories;

import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Repository
public class ImageRepository {
	@Value("${s3.key.access}")
	private String accessKey;

	@Value("${s3.key.secret}")
	private String secretKey;

	@Value("${s3.bucket.endpoint}")
	private String bucketEndpoint;

	@Value("${s3.bucket.region}")
	private String region;
	
	// TODO: Task 1
	@Autowired
	private AmazonS3 S3;

	public String uploadFile(MultipartFile file) throws IOException{

	//    Map<String, String> userData = new HashMap<>();
	//    userData.put("firstName", firstName);
	//    userData.put("lastName", lastName);
	//    userData.put("phone", phone);
	//    userData.put("comments", comments);

	// System.out.println(accessKey + "       DOG        " + secretKey);

		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(file.getContentType());
		metadata.setContentLength(file.getSize());
		String uuid = UUID.randomUUID().toString().substring(0,8);
		InputStream is = file.getInputStream();

		System.out.println(accessKey);
		System.out.println(secretKey);
		System.out.println(bucketEndpoint);
		System.out.println(region);

		PutObjectRequest putReq = new PutObjectRequest("fredbarney", uuid, is, metadata);
		putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);

		S3.putObject(putReq);
		return uuid;
	}
}
