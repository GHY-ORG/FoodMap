package ghy.foodmap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ghy.foodmap.model.Store;
import ghy.foodmap.service.StoreService;

@Controller  
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    StoreService service;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> index()throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        List<Store> stores = new ArrayList<Store>();
        stores  = service.getAll(null);
        if(stores!= null){
            mp.put("stores",stores);
        }else{
            mp.put("stores",null);
        }
        return mp;
    }
    
    @RequestMapping("/findbyid/{id}/{status}")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> findbyid(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id,@PathVariable Integer status) throws Exception{
       
       Map<String, Object> mp = new HashMap<String, Object>();
       Store stores = new Store(); 
       stores  = service.FindById(id,status);
       if(stores != null){
           mp.put("stores", stores);
       }else {
           mp.put("stores",null); 
       }
        return mp;
    }
    
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> delete(@PathVariable int id)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        Store store = service.FindById(id, null);
        if(store == null){
            throw new Exception("Store with id "+id+" not found");
        }
        service.delete(id);
        mp.put("stores",store);
        return mp;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> update(@RequestBody  Store store)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        int id = store.getId();
        Store current = service.FindById(id, null);
        if(current == null){
            throw new Exception("Store with id "+id+" not found");
        }
        store.setId(id);
        service.update(store);
        mp.put("stores",store);
        return mp;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> add(@RequestBody  Store store)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        service.add(store);
        mp.put("stores",store);
        return mp;
    }
}
