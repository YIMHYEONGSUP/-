package practice.jpamybatis.mapper

import org.apache.ibatis.annotations.Mapper
import practice.jpamybatis.entity.People

@Mapper
interface PeopleMapper {

 fun findAll() : List<People>
}