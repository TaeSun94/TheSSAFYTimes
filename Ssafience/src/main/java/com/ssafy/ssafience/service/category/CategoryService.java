package com.ssafy.ssafience.service.category;

import java.util.List;

import com.ssafy.ssafience.model.category.CategoryResult;

public interface CategoryService {
	public List<CategoryResult> getRegionList() throws Exception;	// category_manage_no = 1
	public List<CategoryResult> getUnitList(int categoryNo) throws Exception; // categoryNoParent = categoryNo && category_manage_no = 2
	public List<CategoryResult> getTrackList(int categoryNo) throws Exception; // categoryNoParent = categoryNo && category_manage_no = 3
	public List<CategoryResult> getBoardTrackList() throws Exception; // category_manage_no = 3
	public List<CategoryResult> getSkillAndLanguageList() throws Exception;	// category_manage_no = 4
	public List<CategoryResult> getArticleList() throws Exception;	// category_manage_no = 5
	public List<CategoryResult> getProjectList() throws Exception;	// category_manage_no = 6
}
