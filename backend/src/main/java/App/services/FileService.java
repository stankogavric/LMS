package App.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import App.models.PersonalData;
import App.models.Topic;

@Service
public class FileService {

	public void saveProfileImage(MultipartFile file, String fileName, PersonalData pData) throws IOException {
	    Tika tika = new Tika();
	    String mimeType = tika.detect(file.getBytes());
		if(file != null && (mimeType.equals("image/png") || mimeType.equals("image/jpeg"))) {
			File convertFile = new File("src/main/resources/images/profile_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(file.getBytes());
			fout.close();
			pData.setProfilePicturePath("images/profile_images/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
		}
	}
	
	public void saveTopicIcon(MultipartFile file, String fileName, Topic tData) throws IOException {
	    Tika tika = new Tika();
	    String mimeType = tika.detect(file.getBytes());
		if(file != null && (mimeType.equals("image/png") || mimeType.equals("image/jpeg"))) {
			File convertFile = new File("src\\main\\resources\\images\\topic_icons\\" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(file.getBytes());
			fout.close();
			tData.setIconPath("images/topic_icons/" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
		}
	}
	
}
