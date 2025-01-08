package ru.safiullina.model;

import org.mapstruct.Mapper;

/**
 * Этот интерфейс переводит один объект в другой.
 * Например, у нас есть PostDto который мы отдаем клиенту по контракту.
 * Мы создали класс Post, он имеет служебные поля, которые нельзя отдавать клиенту.
 * Для этого перед тем как отдать клиенту Post мы его переводим в PostDto.
 * Применила инструкции <a href="https://www.baeldung.com/mapstruct">MapStruct</a>
 */
@Mapper(componentModel = "spring")
public interface PostPostDtoMapper {

    Post mapPostDtoToPost(PostDto postDto);

    PostDto mapPostToPostDto(Post post);
}
