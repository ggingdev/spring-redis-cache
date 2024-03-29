package io.cloudtype.springredis.service;

import io.cloudtype.springredis.model.Posts;
import io.cloudtype.springredis.repository.PostsRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final Logger logger = Logger.getLogger(PostsService.class.getName());

    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Cacheable(value = "allPost", key = "'posts'")
    public List<Posts> getAllPosts() {
        logger.info("데이터베이스에서 모든 글을 받아오고 있습니다.");
        List<Posts> posts = postsRepository.findAll();
        logger.info(posts.size() + " 개의 글이 불러오기 되었습니다.");
        return posts;
    }

    @CacheEvict(value = "allPost", allEntries = true)
    public void deletePost(Long id) {
        logger.info(id + " ID의 글이 삭제됩니다.");
        postsRepository.deleteById(id);
        logger.info(id + " ID의 글의 삭제가 완료되었습니다.");
    }

    public Posts savePost(Posts post) {
        postsRepository.save(post);
        return post;
    }
}
