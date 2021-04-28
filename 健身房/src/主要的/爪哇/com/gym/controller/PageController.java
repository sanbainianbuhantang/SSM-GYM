package com.gym.controller;

import com.gym.pojo.ClassTable;
import com.gym.pojo.Page;
import com.gym.service.ClassTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private ClassTableService classTableService;


    @RequestMapping("/fenye.do")
    public String fen(Page page, HttpServletRequest request) {

        try {
            //查询出的list数据
            List<ClassTable> showdata = classTableService.findClassTable();
            //刚开始的页面为第一页
            if (page.getCurrentPage() == null) {
                page.setCurrentPage(1);
            } else {
                page.setCurrentPage(page.getCurrentPage());
            }
            //设置每页数据为十条
            page.setPageSize(5);
            //每页的开始数
            page.setStar((page.getCurrentPage() - 1) * page.getPageSize());
            //list的大小
            int count = showdata.size();
            //设置总页数
            page.setTotalPage(count % 5 == 0 ? count / 5 : count / 5 + 1);
            //对list进行截取
            page.setDataList(showdata.subList(page.getStar(), count - page.getStar() > page.getPageSize() ? page.getStar() + page.getPageSize() : count));
            //设置作用域
            request.setAttribute("paging", page);
            return "selclasstable";
        }catch (Exception e ){
            e.printStackTrace();
            return "selclasstable";
        }
    }

}
