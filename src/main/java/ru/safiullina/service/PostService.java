package ru.safiullina.service;

import org.springframework.stereotype.Service;
import ru.safiullina.exception.NotFoundException;
import ru.safiullina.model.Post;
import ru.safiullina.model.PostDto;
import ru.safiullina.model.PostPostDtoMapper;
import ru.safiullina.model.PostPostDtoMapperImpl;
import ru.safiullina.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<PostDto> all() {
        List<PostDto> postDtoList = new ArrayList<>();
        for (Post post : repository.all()) {
            if (!post.isRemoved()) {
                postDtoList.add(new PostPostDtoMapperImpl().mapPostToPostDto(post));
            }
        }
        return postDtoList;
    }

    public PostDto getById(long id) {
        Post post = repository.getById(id).orElseThrow(NotFoundException::new);
        if (post.isRemoved()) {
            throw new NotFoundException();
        }
        return new PostPostDtoMapperImpl().mapPostToPostDto(post);
    }

    public PostDto save(PostDto postDto) {
        return new PostPostDtoMapperImpl().mapPostToPostDto(
                repository.save(new PostPostDtoMapperImpl().mapPostDtoToPost(postDto)));
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}

