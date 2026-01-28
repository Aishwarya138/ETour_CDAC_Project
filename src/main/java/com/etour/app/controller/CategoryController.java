package com.etour.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etour.app.dto.CategoryClickResponseDTO;
import com.etour.app.entity.CategoryMaster;
import com.etour.app.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryMaster>> getMainCategories() {
		return ResponseEntity.ok(categoryService.getMainCategories());
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryClickResponseDTO> handleCategoryClick(@PathVariable String categoryId) {
		return ResponseEntity.ok(categoryService.handleCategoryClick(categoryId));
	}

	@GetMapping("/search")
	public ResponseEntity<List<CategoryMaster>> searchCategories(
			@org.springframework.web.bind.annotation.RequestParam(required = false) String query,
			@org.springframework.web.bind.annotation.RequestParam(required = false) Double minBudget,
			@org.springframework.web.bind.annotation.RequestParam(required = false) Double maxBudget,
			@org.springframework.web.bind.annotation.RequestParam(required = false) Integer minDays,
			@org.springframework.web.bind.annotation.RequestParam(required = false) Integer maxDays,
			@org.springframework.web.bind.annotation.RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate startDate,
			@org.springframework.web.bind.annotation.RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate endDate) {

		return ResponseEntity.ok(
				categoryService.searchCategories(query, minBudget, maxBudget, minDays, maxDays, startDate, endDate));
	}
}
