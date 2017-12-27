package ghy.foodmap.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ghy.foodmap.model.Store;

@Repository
public interface StoreDao extends JpaRepository<Store, Serializable>{
    @Query("select s from Store  s where s.id = ?1") 
    Store findById(int id);
    @Query("select s from Store  s where s.id = ?1 and s.status = ?2") 
    Store findById(int id, int status);
    
    Store findByName(String name);
    
    @Query("select s from Store  s where s.district = ?1")
    List<Store> findByDistrict(int district);
    @Query("select s from Store  s where s.district = ?1 and s.status = ?2")
    List<Store> findByDistrict(int district,int status);
    
    @Query("select s from Store  s where s.type = ?1")
    List<Store> findByType(int type);
    @Query("select s from Store  s where s.type = ?1 and s.status = ?2")
    List<Store> findByType(int type,int status);
    
    List<Store> findByDistrictAndType(int district,int type);
    @Query("select s from Store  s where s.district = ?1 and s.type = ?2 and s.status = ?3")
    List<Store> findByDistrictAndType(int district,int type,int status);
    
    List<Store> findByStatus(Integer status);
    
    @Query("select s from Store s")
    List<Store> findAll();
    @Query("select s from Store s where s.status =?1")
    List<Store> findAll(int status);
}
