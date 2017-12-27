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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ghy.foodmap.model.Picture;
import ghy.foodmap.model.Store;
import ghy.foodmap.model.Type;
import ghy.foodmap.service.PicService;

@Controller  
@RequestMapping("/pics")
public class PicController {
    @Autowired
    PicService service;
    
    @RequestMapping(value="",method = RequestMethod.GET)
    @ResponseBody//��ʾ���ص��Ǹ�json����ᾭ�������ļ�ת��
    public Map<String, Object> getAll(HttpServletRequest request,HttpServletResponse response)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        List<Picture> pics = new ArrayList<Picture>();
        pics  = service.getAll(null);
//        List<PicShow> picsShow = new ArrayList<PicShow>();
//        for(Picture pic:pics){
//            String ctxPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");  
//            String picPath = ctxPath + pic.getPic();  
//            MultipartFile file; 
//            FileUtils.copyInputStreamToFile(File(picPath));
//            PicShow picShow = new PicShow();
//            picShow.setId(pic.getId());
//            picShow.setStoreId(pic.getStoreId());
//            picShow.setPicName(pic.getPic());
//            picShow.setPicture(picture);
//            picShow.setStatus(pic.getStatus());
//        }
        if(pics!= null){
            mp.put("pics",pics);
        }else{
            mp.put("pics",null);
        }
        return mp;
    }
    
    @RequestMapping("/findbyid/{id}/{status}")
    @ResponseBody//��ʾ���ص��Ǹ�json����ᾭ�������ļ�ת��
    public Map<String, Object> findbyid(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id,@PathVariable Integer status) throws Exception{
       
       Map<String, Object> mp = new HashMap<String, Object>();
       Picture pic = new Picture(); 
       pic  = service.FindById(id,status);
       if(pic != null){
           mp.put("pics", pic);
       }else {
           mp.put("pics",null); 
       }
        return mp;
    }
    
    @RequestMapping("/findbypath/{path}")
    @ResponseBody//��ʾ���ص��Ǹ�json����ᾭ�������ļ�ת��
    public Map<String, Object> findbyname(HttpServletRequest request, HttpServletResponse response,@PathVariable String path) throws Exception{
       Map<String, Object> mp = new HashMap<String, Object>();
       List<Picture> pics = service.FindByPath(path);
       if(pics != null){
           mp.put("pics", pics);
       }else {
           mp.put("pics",null); 
       }
        return mp;
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseBody//��ʾ���ص��Ǹ�json����ᾭ�������ļ�ת��
    public Map<String, Object> delete(@PathVariable int id)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        Picture pic = service.FindById(id, null);
        if(pic == null){
            throw new Exception("Picture with id "+id+" not found");
        }
        service.delete(id);
        mp.put("pics",pic);
        return mp;
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody//��ʾ���ص��Ǹ�json����ᾭ�������ļ�ת��
    /**����ͼƬ����Ϣ����Ҫָ��id��pic��storeId��
     * �޸ĳɹ���json��ʽ����������*/
    public Map<String, Object> update(@RequestBody  Picture pic)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        int id = pic.getId();
        System.out.println(pic.getPic().toString());
        Picture current = service.FindById(id, null);
        if(current == null){
            throw new Exception("Picture with id "+id+" not found");
        }
        current.setId(id);
        service.update(pic);
        mp.put("pics",pic);
        return mp;
    }
    
    @RequestMapping(value="/uploadPic",method = RequestMethod.POST)
    @ResponseBody//��ʾ���ص��Ǹ�json����ᾭ�������ļ�ת��
    /**�ϴ�ͼƬ�ļ�����Ҫ��form��ָ��storeId�����ļ����������ļ��Ḳ�Ǿ��ļ����ϴ���ַ/WEB-INF/upload��
     * �ļ��ϴ��ɹ���json��ʽ�������ݿ��ڲ��������*/
    public Map<String, Object> add(@RequestParam("picFile") MultipartFile myfile, HttpServletRequest request)throws Exception{
        if(request.getParameter("storeId").equals(null)||request.getParameter("storeId").equals(""))
            throw new Exception("storeId not found");
        int storeId = Integer.parseInt(request.getParameter("storeId"));
        String picPath = null;
        System.out.println("storeId: "+storeId);
        
        if(myfile == null){
            System.out.println("�ļ�δ�ϴ�");
            throw new Exception(" file not found");
        }else{
            System.out.println("�ļ�����: " + myfile.getSize());
            System.out.println("�ļ�����: " + myfile.getContentType());
            System.out.println("�ļ�����: " + myfile.getName());
            System.out.println("�ļ�ԭ��: " + myfile.getOriginalFilename());
            picPath = myfile.getOriginalFilename();
            System.out.println("========================================");
            //����õ���Tomcat�����������ļ����ϴ���\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\�ļ�����
            String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
            //���ﲻ�ش���IO���رյ����⣬��ΪFileUtils.copyInputStreamToFile()�����ڲ����Զ����õ���IO���ص������ǿ�����Դ���֪����
            FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));
        }
        System.out.println("uploaded");
        Map<String, Object> mp = new HashMap<String, Object>();
        Picture pic = new Picture();
        pic.setStoreId(storeId);
        pic.setPic(picPath);
        service.add(pic);
        mp.put("pics",pic);
        return mp;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody//��ʾ���ص��Ǹ�json����ᾭ�������ļ�ת��
    public Map<String, Object> addRecord(@RequestBody  Picture picture)throws Exception{
        Map<String, Object> mp = new HashMap<String, Object>();
        service.add(picture);
        mp.put("pics",picture);
        return mp;
    }
    
    @RequestMapping("/downLoadPic/{fileName}/{fileSuffix}")
    @ResponseBody
    public String download(@PathVariable("fileName") String fileName, @PathVariable String fileSuffix, HttpServletRequest request, HttpServletResponse response) throws Exception {  
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
