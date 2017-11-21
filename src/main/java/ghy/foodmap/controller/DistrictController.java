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

import ghy.foodmap.model.District;
import ghy.foodmap.service.DistrictService;

@Controller  
@RequestMapping("/districts")
public class DistrictController {
    @Autowired
    DistrictService service;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> index()throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        List<District> districts = new ArrayList<District>();
        districts  = service.getAll();
        if(districts!= null){
            mp.put("districts",districts);
        }else{
            mp.put("districts",null);
        }
        return mp;
    }
    
    @RequestMapping("/findbyid/{id}/{status}")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> findbyid(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id,@PathVariable Integer status) throws Exception{
    
       Map<String, Object> mp = new HashMap<String, Object>();
       District district = new District(); 
       district  = service.FindById(id,status);
       if(district != null){
           mp.put("districts", district);
       }else {
           mp.put("districts",null); 
       }
        return mp;
    }
    
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> delete(@PathVariable int id)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        District district = service.FindById(id, 1);
        if(district == null){
            throw new Exception("District with id "+id+" not found");
        }
        service.delete(id);
        mp.put("districts",district);
        return mp;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> update(@ModelAttribute  District district)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        int id = district.getId();
        District current = service.FindById(id, 1);
        if(current == null){
            throw new Exception("District with id "+id+" not found");
        }
        district.setId(id);
        service.update(district);
        mp.put("districts",district);
        return mp;
    }
    
    @RequestMapping(value="",method = RequestMethod.POST)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> add(@ModelAttribute  District district)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        service.add(district);
        mp.put("districts",district);
        return mp;
    }
}
