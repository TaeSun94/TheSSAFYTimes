package com.ssafy.ssafience.repo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ssafience.model.dto.Category;

@Mapper
public interface CategoryRepo {
	public List<Category> getRegionList();	// category_manage_no = 1
	public List<Category> getUnitList(int categoryNo); // categoryNoParent = categoryNo && category_manage_no = 2
	public List<Category> getTrackList(int categoryNo); // categoryNoParent = categoryNo && category_manage_no = 3
	public List<Category> getBoardTrackList(); // ategory_manage_no = 3
	public List<Category> getSkillAndLanguageList();	// category_manage_no = 4
	public List<Category> getArticleList();	// category_manage_no = 5
	public List<Category> getProjectList();	// category_manage_no = 6
	public List<String> getInterestedAndSkillList(List<String> item);
}
