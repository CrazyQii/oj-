package org.example.oj.service;

import org.example.oj.entity.Tag;
import org.example.oj.response.RestResponseVO;
import org.example.oj.response.TagVO;

import java.util.List;

public interface TagService {

     RestResponseVO<List<TagVO>> listParentVOAll();

     RestResponseVO insert(Tag tag);

     RestResponseVO delById(Integer id);

     RestResponseVO updateById(Tag tag);

    RestResponseVO getById(Integer tagId);

    RestResponseVO list2Page(Integer pageNum, Integer pageSize, String keyword);

    RestResponseVO<List<Tag>> listAll();
}
