package ghy.foodmap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ghy.foodmap.dao.DistrictDao;
import ghy.foodmap.model.District;
import ghy.foodmap.model.Store;

@Service
public class DistrictService {
    @Autowired
    DistrictDao districtDao;
    
    public List<District> getAll(Integer status){
        if(status != null){
            return districtDao.findAll(status);
        }else{
            return districtDao.findAll();
        }
    }
    
    public void delete(int id){
        districtDao.delete(id);
    }
    
    public District update(District district){
        return districtDao.save(district);
    }
    
    public District add(District district){
        return districtDao.save(district);
    }
    
    public District FindById(Integer id,Integer status){
        if(status != null){
            return districtDao.findById(id,status);
        }else{
            return districtDao.findById(id);
        }
    }
    public List<District> FindByStatus(int status){
        return districtDao.findByStatus(status);
    }
}
