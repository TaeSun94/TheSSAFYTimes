package com.ssafy.ssafience.service.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.category.CategoryResult;
import com.ssafy.ssafience.model.dto.Category;
import com.ssafy.ssafience.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo repo;

	@Override
	public List<CategoryResult> getRegionList() throws Exception {
		List<Category> list = repo.getRegionList();
		List<CategoryResult> regionList = new ArrayList<CategoryResult>();
		
		for (Category cate : list) {
			regionList.add(new CategoryResult(cate.getCategoryNo(), cate.getCategoryName()));
		}
		
		return regionList;
	}

	@Override
	public List<CategoryResult> getUnitList(int categoryNo) throws Exception {
		List<Category> list = repo.getUnitList(categoryNo);
		List<CategoryResult> regionList = new ArrayList<CategoryResult>();
		
		for (Category cate : list) {
			regionList.add(new CategoryResult(cate.getCategoryNo(), cate.getCategoryName()));
		}
		
		return regionList;
	}

	@Override
	public List<CategoryResult> getTrackList(int categoryNo) throws Exception {
		List<Category> list = repo.getTrackList(categoryNo);
		List<CategoryResult> regionList = new ArrayList<CategoryResult>();
		
		for (Category cate : list) {
			regionList.add(new CategoryResult(cate.getCategoryNo(), cate.getCategoryName()));
		}
		
		return regionList;
	}
	
	@Override
	public List<CategoryResult> getBoardTrackList() throws Exception {
		List<Category> list = repo.getBoardTrackList();
		List<CategoryResult> regionList = new ArrayList<CategoryResult>();
		
		for (Category cate : list) {
			regionList.add(new CategoryResult(cate.getCategoryNo(), cate.getCategoryName()));
		}
		
		return regionList;
	}
	
	@Override
	public List<CategoryResult> getSkillAndLanguageList() throws Exception {
		List<Category> list = repo.getSkillAndLanguageList();
		List<CategoryResult> regionList = new ArrayList<CategoryResult>();
		
		for (Category cate : list) {
			regionList.add(new CategoryResult(cate.getCategoryNo(), cate.getCategoryName()));
		}
		
		return regionList;
	}
	
	@Override
	public List<CategoryResult> getArticleList() throws Exception {
		List<Category> list = repo.getArticleList();
		List<CategoryResult> regionList = new ArrayList<CategoryResult>();
		
		for (Category cate : list) {
			regionList.add(new CategoryResult(cate.getCategoryNo(), cate.getCategoryName()));
		}
		
		return regionList;
	}
	
	@Override
	public List<CategoryResult> getProjectList() throws Exception {
		List<Category> list = repo.getProjectList();
		List<CategoryResult> regionList = new ArrayList<CategoryResult>();
		
		for (Category cate : list) {
			regionList.add(new CategoryResult(cate.getCategoryNo(), cate.getCategoryName()));
		}
		
		return regionList;
	}

}
