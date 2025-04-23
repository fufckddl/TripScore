package com.example.demo.map;

import com.example.demo.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MapService {
    private final MapRepository mapRepository;

    public void create(MapDto dto, SiteUser user)
    {
        Map map = new Map();
        map.setAddress(dto.getAddress());
        map.setTitle(dto.getTitle());
        map.setContent(dto.getContent());
        map.setSiteUser(user);

        mapRepository.save(map);
    }
    public void update(Long id, MapDto dto)
    {
        Map map = getMapId(id);
        map.setTitle(dto.getTitle());
        map.setContent(dto.getContent());
        map.setAddress(dto.getAddress());
        mapRepository.save(map);
    }
    public void delete(Long id)
    {
        mapRepository.deleteById(id);
    }
    public List<Map> findAll(){
        return mapRepository.findAll();
    }
    public Map findById(Long id)
    {
        return mapRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("게시글/숙소를 찾을 수 없습니다."));
    }

    public Map getMapId(Long id)
    {
        return mapRepository.findById(id).orElseThrow(()-> new RuntimeException("게시글/숙소를 찾을 수 없습니다."));
    }
}
