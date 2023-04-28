package practice.jpamybatis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practice.jpamybatis.entity.People;
import practice.jpamybatis.repository.PeopleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeopleService {

    @Autowired
    private final PeopleRepository peopleRepository;

    public List<People> findAll(){
        return peopleRepository.findAll();
    }
}
