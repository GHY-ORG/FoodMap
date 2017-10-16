package ghy.foodmap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ghy.foodmap.dao.PicDao;
import ghy.foodmap.model.Picture;

@Service
public class PicService {
    @Autowired
    PicDao pics;
    
    public List<Picture> FindByStore(int storeId, int status){
        return pics.findByStore(storeId, status);
    }

}
