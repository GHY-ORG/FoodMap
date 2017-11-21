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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ghy.foodmap.model.Type;
import ghy.foodmap.service.TypeService;

@Controller  
@RequestMapping("/types")
public class TypeController {
    @Autowired
    TypeService service;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> index()throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        List<Type> types = new ArrayList<Type>();
        types  = service.getAll();
        if(types!= null){
            mp.put("types",types);
        }else{
            mp.put("types",null);
        }
        return mp;
    }
    
    @RequestMapping("/findbyid/{id}/{status}")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> findbyid(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id,@PathVariable Integer status) throws Exception{
      
       Map<String, Object> mp = new HashMap<String, Object>();
       Type type = new Type(); 
       type  = service.FindById(id,status);
       if(type != null){
           mp.put("types", type);
       }else {
           mp.put("types",null); 
       }
        return mp;
    }
    
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> delete(@PathVariable int id)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        Type type = service.FindById(id, 1);
        if(type == null){
            throw new Exception("Type with id "+id+" not found");
        }
        service.delete(id);
        mp.put("types",type);
        return mp;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> update(@ModelAttribute  Type type)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        int id = type.getId();
        Type current = service.FindById(id, 1);
        if(current == null){
            throw new Exception("District with id "+id+" not found");
        }
        type.setId(id);
        service.update(type);
        mp.put("stores",type);
        return mp;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> add(@ModelAttribute  Type type)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        service.add(type);
        mp.put("stores",type);
        return mp;
    }
}
