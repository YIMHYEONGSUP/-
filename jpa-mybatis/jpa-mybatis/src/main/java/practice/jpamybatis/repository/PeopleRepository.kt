package practice.jpamybatis.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import practice.jpamybatis.entity.People

@Repository
interface PeopleRepository : JpaRepository<People, Long>{

}