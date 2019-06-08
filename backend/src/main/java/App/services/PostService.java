package App.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import App.models.Post;
import App.repositories.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepo;

    public PostService() {
    }

    public Iterable<Post> getPosts() {
        return postRepo.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepo.findById(id);
    }

    public void addPost(Post post) {
        postRepo.save(post);
    }

    public void removePost(Long id) {
        Optional<Post> post = postRepo.findById(id);
        postRepo.delete(post.get());
    }

    public void updatePost(Long id, Post post) {
        Optional<Post> Pos = postRepo.findById(id);
        if(Pos.isPresent()) {
            post.setId(Pos.get().getId());
            postRepo.save(post);
        }
    }

}
