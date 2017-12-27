package ghy.foodmap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ghy.foodmap.dao.PicDao;
import ghy.foodmap.model.Picture;
import ghy.foodmap.model.Store;

@Service
public class PicService {
    @Autowired
    PicDao picDao;
    
    public List<Picture> getAll(Integer status){
        if(status != null){
            return picDao.findAll(status);
        }else{
            return picDao.findAll();
        }
    }
    
    public List<Picture> FindByStore(int storeId, int status){
        return picDao.findByStore(storeId, status);
    }
    
    public Picture FindById(int id, Integer status){
        if(status != null){
            return picDao.findById(id,status);
        }else{
            return picDao.findById(id);
        }
    }
    
    public List<Picture> FindByPath(String path){
        return picDao.findByPic(path);
    }
    public void delete(int id){
        picDao.delete(id);
    }
    
    public void deleteByStore(int storeId){
        List<Picture> pList = picDao.findByStore(storeId);
        picDao.delete(pList);
    }
    
    public Picture update(Picture pic){
        return picDao.save(pic);
    }

    public Picture add(Picture pic){
        return picDao.save(pic);
    }
}
