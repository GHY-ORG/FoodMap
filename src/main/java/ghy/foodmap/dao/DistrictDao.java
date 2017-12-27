package ghy.foodmap.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ghy.foodmap.model.District;
import ghy.foodmap.model.Store;

@Repository
public interface DistrictDao extends JpaRepository<District, Serializable> {
    @Query("select d from District  d where d.id = ?1") 
    District findById(int id);
    @Query("select d from District  d where d.id = ?1 and d.status = ?2") 
    District findById(int id, int status);
    District findByName(String Name);
    List<District> findByStatus(int status);
    
    List<District> findAll();
    @Query("select d from District d where d.status =?1")
    List<District> findAll(int status);
}
