package com.cssl.controller;

import com.cssl.pojo.Item;
import com.cssl.pojo.Options;
import com.cssl.pojo.Subject;
import com.cssl.pojo.User;
import com.cssl.service.ItemService;
import com.cssl.service.OptionService;
import com.cssl.service.SubjectService;
import com.cssl.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {

   @Autowired
    private UserService Uservice;
   @Autowired
   private SubjectService subservice;
   @Autowired
   private ItemService itemservice;
   @Autowired
    private OptionService optionservice;

    //登录验证
    @RequestMapping("logining")
    public ModelAndView login( User user, ModelAndView model,HttpSession session) {
    User u1=Uservice.login(user);
    if (u1!=null)
    {
        model.addObject("user",u1);
        session.setAttribute("user",u1);
        ServletContext application=session.getServletContext();
        List<String> list=(List<String>)application.getAttribute("users");
        if(list.contains(user.getUsername())){
            System.out.println("已经存在,登录失败");
            model.setViewName("login");
        }else{
            list.add(u1.getUsername());
            model.setViewName("forward:deng");
        }

    }else
        {
            System.out.println("登录失败");
            model.setViewName("login");
        }
    return model;
    }
    //登录成功之后进行查询数据
    @RequestMapping("deng")
    public String cha(HttpSession session,Integer pageindex,String title)
    {
        PageHelper.startPage(pageindex==null?1:pageindex,5);
        List<Map<String,Object>> list=Uservice.selectall(title == null ? "%" : title);
        session.setAttribute("list",list);
        session.setAttribute("title",title);
        return "votelist";
    }
    //跳转维护页面带值
    @RequestMapping("weihu")
    public String weihu(HttpSession session,Integer pageindex,String title)
    {
        PageHelper.startPage(pageindex==null?1:pageindex,5);
        List<Map<String,Object>> list=Uservice.selectall(title == null ? "%" : title);
        session.setAttribute("list",list);
        session.setAttribute("title",title);
        return "weihulist";
    }
    //维护页面的删除
    @RequestMapping("/delwei")
    public String del(Integer sid,HttpSession session)
    {
        int item=itemservice.idel(sid);
        if (item>0)
        {
            int opt=optionservice.odel(sid);
            if (opt>0)
            {
                int sub=subservice.sdel(sid);
                if (sub>0)
                {
                    System.out.println(sub+"删除成功");
                }
            }
        }
        return "forward:weihulist";
    }
    //注册（判断是否同名）
    @RequestMapping("reg")
    public ModelAndView zhuce(ModelAndView model, HttpServletRequest request,User user) throws UnsupportedEncodingException
    {
        System.out.print(user.getUsername());
        int yong =  Uservice.Cun(user.getUsername());
        if(yong > 0){
            model.setViewName("regist");
        }else {
            int cheng = Uservice.regist(user);
            if (cheng > 0) {
                System.out.println("注册成功");
                model.setViewName("login");
            }
        }
        return model;
    }
    //点击查看详细信息
    @RequestMapping("/selectone")
    public String selectone(Integer id,HttpSession session)
    {
        Map<String,Object> map=subservice.selectone(id);
       List<Map<String,Object>> listmap=subservice.xiang(id);
        session.setAttribute("map",map);
       session.setAttribute("listmap",listmap);
        return "/voteview";
    }
    //点击跳转维护界面
    @RequestMapping("/weihuvi")
    public String weihu(Integer sid,HttpSession session)
    {
        System.out.println("sid"+sid);
        Map<String,Object> map=subservice.selectone(sid);
        List<Map<String,Object>> listmap=subservice.xiang(sid);
        session.setAttribute("map",map);
        session.setAttribute("listmap",listmap);
        return "/weihuview";
    }
    //点击我要参与
    @RequestMapping("/selectid")
    public String selectid(Integer id,HttpSession session)
    {
        Map<String,Object> map=itemservice.selectid(id);
        List<Map<String,Object>> listmap=itemservice.xiangxi(id);
        session.setAttribute("map",map);
        session.setAttribute("listmap",listmap);
        return "/vote";
    }
    //进行投票处理
    @RequestMapping("/Vote-voteSave.action")
    public String toupiao(HttpSession session, Integer sid, @RequestParam List<String> oid1)
    {
        User user=(User) session.getAttribute("user");
        int ff=0;
        Subject sub=new Subject();
        sub.setSid1(sid);
        for (String s : oid1) {
            Options o = new Options();
            o.setOid1(Integer.valueOf(s));
            Item i=new Item();
            i.setUid1(user.getUid1());
            i.setOid1(o.getOid1());
            i.setSid1(sub.getSid1());
            int tian=itemservice.toupiao(i);
            ff+=tian;
        }
        if (ff>0)
        {
            System.out.println("投票成功");
            return "forward:deng";
        }else
            {
                return "forward:deng";
            }
    }
    @RequestMapping("xiugai")
    public String update(int sid1, Model mod, @RequestParam List<String> options, Subject sub,
     @RequestParam List<String> oids){
        List<Map<String,Object>> list = optionservice.xiangxi(sid1);
        sub.setSid1(sid1);
        //修改
        for(int i=0;i<oids.size();i++){
            Options o2 = new Options();
            o2.setContent1(options.get(i));
            o2.setOsid(sub.getSid1());
            o2.setOid1(Integer.valueOf(oids.get(i)));
            optionservice.oup(o2);
        }

        for(int i=oids.size();i<=options.size()-1;i++){
            System.out.println(oids.size()+"-"+options.size());
            Options o = new Options();
            o.setContent1(options.get(i));
            o.setOsid(sub.getSid1());
            optionservice.oins(o);
        }

        /*if(newoptions.size() > 0 || newoptions != null){
            //增加了选项
            for(int i=0;i<newoptions.size();i++){
                System.out.println("aaa"+newoptions.get(i));
                Options o = new Options();
                o.setContent1(newoptions.get(i));
                o.setOsid(sub.getSid1());
                optionservice.oins(o);
            }
        }*/
        for(Map<String,Object> m : list){
            if(!oids.contains(m.get("OID1").toString())){
                System.out.println(m.get("OID1"));
                int oid = Integer.valueOf(m.get("OID1").toString());
                int yong = itemservice.delbyoid(oid);
                if(yong >= 0){
                    optionservice.delbyoid(oid);
                }
            }
        }
        subservice.sup(sub);
        return "forward:deng";
    }
    @RequestMapping("zuxiao")
    public String zuxiao(HttpSession session)
    {
        session.invalidate();
        return "redirect:login";
    }



}
