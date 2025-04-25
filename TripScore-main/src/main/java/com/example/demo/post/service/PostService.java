package com.example.demo.post.service;

import com.example.demo.post.repository.PostRepository;
import com.example.demo.post.dto.PostDto;
import com.example.demo.post.entitiy.Post;
import com.example.demo.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository mapRepository;

    public void create(PostDto dto, SiteUser user)
    {
        Post map = new Post();
        map.setAddress(dto.getAddress());
        map.setTitle(dto.getTitle());
        map.setContent(dto.getContent());
        map.setSiteUser(user);

        mapRepository.save(map);
    }
    public void update(Long id, PostDto dto)
    {
        Post map = getMapId(id);
        map.setTitle(dto.getTitle());
        map.setContent(dto.getContent());
        map.setAddress(dto.getAddress());
        mapRepository.save(map);
    }
    public void delete(Long id)
    {
        mapRepository.deleteById(id);
    }
    public List<Post> findAll(){
        return mapRepository.findAll();
    }
    public Post findById(Long id)
    {
        return mapRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("게시글/숙소를 찾을 수 없습니다."));
    }

    public Post getMapId(Long id)
    {
        return mapRepository.findById(id).orElseThrow(()-> new RuntimeException("게시글/숙소를 찾을 수 없습니다."));
    }
}
