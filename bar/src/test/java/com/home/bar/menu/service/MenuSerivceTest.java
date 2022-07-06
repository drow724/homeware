package com.home.bar.menu.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.home.bar.menu.MenuTest;
import com.home.bar.menu.dto.MenuDto;

public class MenuSerivceTest extends MenuTest {
	
	@Test
	public void get() {

		//given
		//when
		List<MenuDto> list = menuService.getMenuList();

		//then
		compare(list, mockMenuList);
	}
	
	@Test
	public void save() {

		//given
		MenuDto dto = MenuDto.builder().name("신메뉴").description("새로운 메뉴").disabled(0).build();
		
		//when
		menuService.save(dto);
		mockMenuList.add(dto);
		
		//then
		compare(menuService.getMenuList(), mockMenuList);
	}
	
	@Test
	public void update() {

		//given
		List<MenuDto> list = menuService.getMenuList();
		
		//when
		for(MenuDto dto : list) {
			menuService.update(dto);
		}

		//then
		compare(menuService.getMenuList(), list);
	}
	
	@Test
	public void delete() {
		
		//given
		List<MenuDto> list = menuService.getMenuList();
		
		//when
		for(MenuDto menu : list) {
			menuService.delete(menu.getId());
		}
		
		//then
		menuService.getMenuList().stream().forEach(m -> assertThat(m.getDisabled()).isEqualTo(1));
	}

}
