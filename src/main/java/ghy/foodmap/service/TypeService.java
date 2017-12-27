package ghy.foodmap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ghy.foodmap.dao.TypeDao;
import ghy.foodmap.model.Store;
import ghy.foodmap.model.Type;

@Service
public class TypeService {
    @Autowired
    TypeDao typeDao;
    
    public List<Type> getAll(Integer status){
        if(status != null){
            return typeDao.findAll(status);
        }else{
            return typeDao.findAll();
        }
    }
    
    public void delete(int id){
        typeDao.delete(id);
    }
    
    public Type update(Type type){
        return typeDao.save(type);
    }
    
    public Type add(Type type){
        return typeDao.save(type);
    }
    
    public Type FindById(Integer id,Integer status){
        if(status != null){
            return typeDao.findById(id,status);
        }else{
            return typeDao.findById(id);
        }
    }
    public List<Type> FindByStatus(int status){
        return typeDao.findByStatus(status);
    }
}
