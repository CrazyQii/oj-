package org.example.oj.controller.portal;

import org.example.oj.entity.BlogCategory;
import org.example.oj.response.RestResponseVO;
import org.example.oj.response.Select2VO;
import org.example.oj.service.BlogCategoryService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/blogCategory")
public class BlogCategoryController {

    @Resource
    private BlogCategoryService blogCategoryService;


    /**
     * 返回所有博客分类给select2　
     * @return
     */
    @ResponseBody
    @RequestMapping("/listSelect2")
    public List<Select2VO> listSelect2() {
        RestResponseVO<List<BlogCategory>> responseVO = blogCategoryService.listAll();
        List<BlogCategory> data = responseVO.getData();
        List<Select2VO> select2VOList = new ArrayList<>();
        data.stream().forEach(blogCategory -> {
            Select2VO select2VO = new Select2VO();
            select2VO.setId(blogCategory.getId());
            select2VO.setText(blogCategory.getName());
            select2VOList.add(select2VO);
        });
        return select2VOList;
    }





}


