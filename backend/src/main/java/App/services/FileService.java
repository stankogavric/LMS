package App.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import App.models.PersonalData;

@Service
public class FileService {

	public void saveProfileImage(MultipartFile file, String fileName, PersonalData pData) throws IOException {
	    Tika tika = new Tika();
	    String mimeType = tika.detect(file.getBytes());
		if(file != null && (mimeType == "image/png" || mimeType == "image/jpeg")) {
			File convertFile = new File("resources\\images\\profile images\\" + fileName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(file.getBytes());
			fout.close();
			pData.setProfilePicturePath(convertFile.getPath());
		}
	}
	
}
