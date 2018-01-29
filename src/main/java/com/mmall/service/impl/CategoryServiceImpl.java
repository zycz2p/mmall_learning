package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by ausu on 2018/1/19.
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    public ServerResponse addCategory(String categoryName,Integer parentId)
    {
        if(parentId == null || StringUtils.isBlank(categoryName))
        {
            return ServerResponse.createByErrorMessage("分类参数错误");
        }
        Category category = new Category();

        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);

        int rowCount = categoryMapper.insert(category);
        if(rowCount > 0)
        {
            return ServerResponse.createBySuccess("品类创建成功");
        }
        return  ServerResponse.createByErrorMessage("品类创建失败");
    }

    public ServerResponse updateCategoryName(Integer categorID,String categoryName)
    {
        if(categorID == null || StringUtils.isBlank(categoryName))
        {
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        Category category = new Category();

        category.setId(categorID);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if(rowCount > 0)
        {
            return ServerResponse.createBySuccess("品类名称修改成功");
        }
        return  ServerResponse.createByErrorMessage("品类名称修改失败");
    }

    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId)
    {
        List<Category> categoryList = categoryMapper.selectCategoryByParentId(categoryId);
        if(CollectionUtils.isEmpty(categoryList))
        {
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    public ServerResponse selectCategoryAndChildrenById(Integer categoryId)
    {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet,categoryId);

        List<Integer> categoryList = Lists.newArrayList();
        if(categoryId != null)
        {
            for(Category category : categorySet)
            {
                categoryList.add(category.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryList);

    }

    private Set<Category>  findChildCategory(Set<Category> categorySet,Integer categoryId)
    {
        Category category  = categoryMapper.selectByPrimaryKey(categoryId);
        if(category !=null)
        {
            categorySet.add(category);
        }
        //查找子节点，递归算法一定要有推出条件
        List<Category> categoryList = categoryMapper.selectCategoryByParentId((categoryId));
        for(Category categoryItem : categoryList)
        {
            findChildCategory(categorySet,categoryItem.getId());
        }
        return categorySet;
    }


}
