package br.com.hugo.publication.service;

import br.com.hugo.publication.domain.Comment;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RedisService {

    private static final String KEY = "Comment";
    private final RedisTemplate<String, Object> redisTemplate;

    public void save(List<Comment> comments, String id){
        redisTemplate.opsForHash().put(KEY, id, comments);
    }

    public List<Comment> findById(String id){
        return (List<Comment>) redisTemplate.opsForHash().get(KEY, id);
    }

}
