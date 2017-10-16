package ghy.foodmap.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ghy.foodmap.model.Type;


@Repository
public interface TypeDao extends JpaRepository<Type, Serializable> {
    @Query("select t from Type  t where t.id = ?1") 
    Type findById(int id);
    @Query("select t from Type  t where t.id = ?1 and t.status = ?2") 
    Type findById(int id, int status);
    Type findByName(String Name);
    List<Type> findByStatus(int status);
}
