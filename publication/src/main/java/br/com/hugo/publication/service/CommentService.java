package br.com.hugo.publication.service;

import br.com.hugo.publication.client.CommentClient;
import br.com.hugo.publication.domain.Comment;
import br.com.hugo.publication.domain.Publication;
import br.com.hugo.publication.exception.FalbackException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService {

    private final CommentClient client;
    private final RedisService redisService;

    @CircuitBreaker(name = "comments", fallbackMethod = "getCommentsFallback")
    public List<Comment> getComments(String id){
        var comments = client.getComments(id);
        redisService.save(comments, id);
        return comments;
    }

    private List<Comment> getCommentsFallback(String id, Throwable cause) {
        log.warn("[WARN] Fallback with Id: {}", id);
        return redisService.findById(id);
    }
}
