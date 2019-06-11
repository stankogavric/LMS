package App.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.tika.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.annotation.JsonView;

import App.models.File;
import App.services.FileService;
import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/file")
public class FileController {	
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value = "/images/{type}/{file}", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> getFileFromImages(@PathVariable String type, @PathVariable String file) throws IOException {
    	FileInputStream fis = new FileInputStream("src/main/resources/images/" + type +"/"+ file);
    	byte[] bytes = IOUtils.toByteArray(fis);
		fis.close();
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }
    
    @RequestMapping(value = "/teaching_materials/{file}/**", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> getFileFromTeachingMaterials(@PathVariable String file, HttpServletRequest request) throws IOException {
    	final String path =
                request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern =
                request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();

        String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);

        String moduleName;
        if (null != arguments && !arguments.isEmpty()) {
            moduleName = file + '/' + arguments;
        } else {
            moduleName = file;
        }

    	FileInputStream fis = new FileInputStream("src/main/resources/teaching_materials/" + moduleName);
    	byte[] bytes = IOUtils.toByteArray(fis);
		fis.close();
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/subject/{id}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Optional<File>>> getTFilesBySubject(@PathVariable Long id) {
        return new ResponseEntity<Iterable<Optional<File>>>(fileService.getFilesBySubject(id), HttpStatus.OK);
    }
	
}
