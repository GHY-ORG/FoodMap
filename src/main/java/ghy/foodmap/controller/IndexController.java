package ghy.foodmap.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ghy.foodmap.model.District;
import ghy.foodmap.model.Picture;
import ghy.foodmap.model.Store;
import ghy.foodmap.model.Type;
import ghy.foodmap.service.DistrictService;
import ghy.foodmap.service.PicService;
import ghy.foodmap.service.StoreService;
import ghy.foodmap.service.TypeService;

@Controller  
@RequestMapping("/")
public class IndexController {
    @Autowired
    StoreService stores;
    @Autowired
    DistrictService districts;
    @Autowired
    TypeService types;
    @Autowired
    PicService pics;
    
    @RequestMapping("/")
    public String index(){
        System.out.println("index");
        return "index.html";
    }
    
    @RequestMapping(value="/district/{status}",method = RequestMethod.GET)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> district(@PathVariable int status)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        List<District> district = new ArrayList<District>();
        district =districts.FindByStatus(status);
        if(district!= null){
            mp.put("district",district);
        }else{
            mp.put("district",null);
        }
        
        return mp;
    }
    
    @RequestMapping(value="/type/{status}",method = RequestMethod.GET)
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> type(@PathVariable int status)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        
        List<Type> type = new ArrayList<Type>();
        type =types.FindByStatus(status);
        if(type!= null){
            mp.put("type",type);
        }else{
            mp.put("type",null);
        }
        return mp;
    }
    
    @RequestMapping("/random/{district}/{type}")
    @ResponseBody//表示返回的是个json对象会经过配置文件转换
    public Map<String, Object> random(@PathVariable int district,@PathVariable int type)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        Store s = new Store();
        s = stores.Random(district,type);
        if(s!= null){
            mp.put("store",s);
            List<Picture> p = new ArrayList<Picture>();
            p = pics.FindByStore(s.getId(), 1);
            if(p!= null){
                mp.put("pics",p);
            }else{
                mp.put("pics",null);
            }
        }else{
            mp.put("store",null);
        }
        return mp;
    }
    
    @RequestMapping("/pic/{fileName}/{fileSuffix}")
    @ResponseBody
    public String downloadPic(@PathVariable("fileName") String fileName, @PathVariable String fileSuffix, HttpServletRequest request, HttpServletResponse response) throws Exception {  
        byte[] byData = new byte[]{};
        try {
            String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
            System.out.println(fileName);
            byData = FileUtils.readFileToByteArray(new File(realPath,fileName+"."+fileSuffix));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String imgBase64Data = null;
        if (byData != null){
            imgBase64Data = "data:image/jpg;base64,"+Base64Utils.encodeToString(byData);
        }
        return imgBase64Data;
    } 
    
}
