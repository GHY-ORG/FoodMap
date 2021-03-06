package ghy.foodmap.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ghy.foodmap.model.Picture;
import ghy.foodmap.model.Store;

@Repository
public interface PicDao extends JpaRepository<Picture, Serializable> {
    @Query("select p from Picture  p where p.id = ?1") 
    Picture findById(int id);
    @Query("select p from Picture  p where p.id = ?1 and p.status = ?2") 
    Picture findById(int id, int status);
    @Query("select p from Picture  p where p.storeId = ?1") 
    List<Picture> findByStore(int storeId);
    @Query("select p from Picture  p where p.storeId = ?1 and p.status = ?2") 
    List<Picture> findByStore(int storeId, int status);
    
    List<Picture> findByStatus(int status);
    
    @Query("select p from Picture  p where p.pic = ?1") 
    List<Picture> findByPic(String name);
    
    List<Picture> findAll();
    @Query("select p from Picture p where p.status =?1")
    List<Picture> findAll(int status);
}
