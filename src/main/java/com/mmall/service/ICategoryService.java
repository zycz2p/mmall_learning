package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ausu on 2018/1/19.
 */

public interface ICategoryService {
     ServerResponse addCategory(String categoryName, Integer parentId);
    ServerResponse updateCategoryName(Integer categorID,String categoryName);
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);
    ServerResponse selectCategoryAndChildrenById(Integer categoryId);
}
