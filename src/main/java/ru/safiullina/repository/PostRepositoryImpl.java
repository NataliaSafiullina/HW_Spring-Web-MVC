package ru.safiullina.repository;

import org.springframework.stereotype.Repository;
import ru.safiullina.exception.NotFoundException;
import ru.safiullina.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryImpl implements PostRepository {

    protected AtomicLong counter = new AtomicLong();
    protected ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();

    /**
     * Получить все посты
     *
     * @return - список постов
     */
    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    /**
     * Получить пост по id
     *
     * @param id - id поста
     * @return - пост
     */
    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    /**
     * Сохранение поста в хранилище
     *
     * @param post - получаем пост
     * @return - сохраненный пост
     */
    public Post save(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("post must not be null");
        }
        // Если от клиента приходит пост с id=0, значит, это создание нового поста.
        if (post.getId() == 0) {
            post.setId(counter.incrementAndGet());
            post.setRemoved(false);
            posts.put(post.getId(), post);
        }
        // Если от клиента приходит пост с id<>0, значит, это обновление уже существующего поста.
        else {
            if (posts.containsKey(post.getId()) && !posts.get(post.getId()).isRemoved()) {
                posts.put(post.getId(), post);
            } else {
                throw new NotFoundException();
            }
        }
        return post;
    }

    /**
     * Удаление поста по ID
     *
     * @param id - id поста
     */
    public void removeById(long id) {
        // Метод remove() возвращает null, если такой записи нет,
        // или возвращает бывшее значение связанное с ключом, если запись была
        if (posts.containsKey(id)) {
            Post post = posts.get(id);
            post.setRemoved(true);
            if (post.isRemoved()) {
                System.out.println("Post with id = " + id + " removed");
            } else {
                System.out.println("Post with id = " + id + " not found");
            }
        } else {
            throw new NotFoundException();
        }
    }

}
