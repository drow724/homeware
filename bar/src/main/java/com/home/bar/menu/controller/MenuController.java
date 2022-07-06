package com.home.bar.menu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.bar.common.response.BaseResponse;
import com.home.bar.menu.dto.MenuDto;
import com.home.bar.menu.service.MenuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

	private final MenuService menuService;
	
	@GetMapping
	public BaseResponse getMenus() {
		BaseResponse response = new BaseResponse();
		response.setStatus(HttpStatus.OK);
		response.setData(menuService.getMenuList());
		return response;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse saveMenu(@RequestBody MenuDto menuDto) {
		menuService.save(menuDto);
		BaseResponse response = new BaseResponse();
		response.setStatus(HttpStatus.OK);
		return response;
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse update(@RequestBody MenuDto menuDto) {
		menuService.update(menuDto);
		BaseResponse response = new BaseResponse();
		response.setStatus(HttpStatus.OK);
		return response;
	}
	
	@DeleteMapping
	public BaseResponse delete(@RequestParam Long id) {
		menuService.delete(id);
		BaseResponse response = new BaseResponse();
		response.setStatus(HttpStatus.OK);
		return response;
	}
}
