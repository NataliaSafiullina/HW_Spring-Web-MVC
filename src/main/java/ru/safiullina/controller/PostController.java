package ru.safiullina.controller;

import org.springframework.web.bind.annotation.*;

import ru.safiullina.model.PostDto;
import ru.safiullina.service.PostService;

import java.util.List;

/**
 * Аннотация RestController включает две аннотации и Controller и ResponseBody,
 * т.е. говорит, что это контроллер и использует конвертер ответов
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<PostDto> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable long id) {
        return service.getById(id);
    }

    /**
     * Тут вступают в дело наши конвертеры,
     * тело запроса post парсится в наш объект Post
     *
     * @param postDto - тело запроса
     * @return - вернем созданный пост
     */
    @PostMapping
    public PostDto save(@RequestBody PostDto postDto) {
        return service.save(postDto);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }
}