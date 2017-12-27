package ghy.foodmap.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ghy.foodmap.dao.StoreDao;
import ghy.foodmap.model.Store;

@Service
public class StoreService {
    @Autowired
    StoreDao storesDao;
    
    public List<Store> getAll(Integer status){
        if(status != null){
            return storesDao.findAll(status);
        }else{
            return storesDao.findAll();
        }
    }
    
    public Store FindById(Integer id,Integer status){
        if(status != null){
            return storesDao.findById(id,status);
        }else{
            return storesDao.findById(id);
        }
    }
    
    public void delete(int id){
        storesDao.delete(id);
    }
    
    public Store update(Store store){
        return storesDao.save(store);
    }
    
    public Store add(Store store){
        return storesDao.save(store);
    }

    public Store Random(int district, int type) {
        List<Store> ss;
        if(district == 0 && type == 0){
            ss = storesDao.findByStatus(1);
        }else if(district == 0){
            ss = storesDao.findByType(type,1);
        }else if(type == 0){
            ss = storesDao.findByDistrict(district,1);
        }else {
            ss = storesDao.findByDistrictAndType(district, type, 1);
        }
        Random random = new Random();
       int s_id = random.nextInt(ss.size());
       return ss.get(s_id);
    }
    

}
