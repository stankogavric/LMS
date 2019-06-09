package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.PostFile;
import App.repositories.PostFileRepository;

@Service
public class PostFileService {

    @Autowired
    private PostFileRepository postFileRepo;

    public PostFileService() {
    }

    public Iterable<PostFile> getPostFiles() {
        return postFileRepo.findAll();
    }

    public Optional<PostFile> getPostFileById(Long id) {
        return postFileRepo.findById(id);
    }

    public void addPostFile(PostFile postFile) {
        postFileRepo.save(postFile);
    }

    public void removePostFile(Long id) {
        Optional<PostFile> postFile = postFileRepo.findById(id);
        postFileRepo.delete(postFile.get());
    }

    public void updatePostFile(Long id, PostFile postFile) {
        Optional<PostFile> Pos = postFileRepo.findById(id);
        if(Pos.isPresent()) {
            postFile.setId(Pos.get().getId());
            postFileRepo.save(postFile);
        }
    }

}
