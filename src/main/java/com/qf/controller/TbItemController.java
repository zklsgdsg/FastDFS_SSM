package com.qf.controller;

import com.qf.pojo.TbItem;
import com.qf.service.TbItemService;
import com.qf.utils.FastDFSClient;
import com.qf.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Controller
public class TbItemController {

    @Resource
    private TbItemService tbItemService;

    @RequestMapping("/findall")
    @ResponseBody
    public R findAll(){
        List<TbItem> items = tbItemService.findAll();

        return R.ok().put("tbItems",items);
    }

    @RequestMapping("/add")
    @ResponseBody
    public R add(@RequestParam(name = "img") MultipartFile file, TbItem tbItem){

        StringBuffer sb = null;
        try {
            String filename = file.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf(".")+1);
            FastDFSClient client = new FastDFSClient("client.conf");
            String[] s = client.uploadFile(file.getBytes(), suffix);
            sb = new StringBuffer("http://192.168.132.147/");
            for(int i=0; i<s.length; i++){  //group1 /M00/00/00/xxx.jpg
                sb.append(s[i]);
                if(i==0){
                    sb.append(File.separator);
                }
            }
            System.out.println("成功"+sb);

        } catch (Exception e) {
            System.out.println("失败");
            e.printStackTrace();
            return R.error("失败");
        }
        String images = sb.toString();
        tbItem.setImages(images);
        boolean b = tbItemService.save(tbItem);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

}
