package com.home.bar.menu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.bar.menu.controller.MenuController;
import com.home.bar.menu.dto.MenuDto;
import com.home.bar.menu.service.MenuService;

@SpringBootTest
@Transactional
public abstract class MenuTest {

	protected final String PRE_FIX = "/";
	
	protected final String DOMAIN = "menu";
	
	@Autowired
	protected MenuController menuController;
	
	@Autowired
	protected MenuService 	menuService;
	
	protected List<MenuDto>	mockMenuList = new ArrayList<>();
	
	protected ObjectMapper mapper = new ObjectMapper();
	
	protected MockMvc mockMvc;
	
	@BeforeEach
	public void init() throws Exception {
		
		mockMvc = MockMvcBuilders.standaloneSetup(menuController).addFilters(new CharacterEncodingFilter("UTF-8", true))
				.build();
		
		MenuDto dto1 = MenuDto.builder().name("¿¬¾î »ø·¯µå").description("°í¼ÒÇÑ ¿¬¾î»ø·¯µå").disabled(0).build();
		mockMenuList.add(dto1);
		
		MenuDto dto2 = MenuDto.builder().name("ºÒ´ß ººÀ½¸é").description("È­²öÇÑ ºÒ´ß ººÀ½¸é").disabled(0).build();
		mockMenuList.add(dto2);
		
		MenuDto dto3 = MenuDto.builder().name("½Å¶ó¸é").description("¾óÅ«ÇÑ ½Å¶ó¸é").disabled(0).build();
		mockMenuList.add(dto3);
		
		MenuDto dto4 = MenuDto.builder().name("½ºÆÔ").description("Â¬Â©ÇÑ ½ºÆÔ").disabled(0).build();
		mockMenuList.add(dto4);

		for(MenuDto menu : mockMenuList) {
			menuService.save(menu);
		}
	};
	
	public void get() throws Exception {};
	
	public void save() throws Exception {};
	
	public void update() throws Exception {};
	
	public void delete() throws Exception {};
	
	public void compare(List<MenuDto> list1, List<MenuDto> list2) {
		list1.stream().forEach(m -> list2.stream().forEach(menu -> {
			if(m.getId() == menu.getId()) {
				assertThat(m.getName()).isEqualTo(menu.getName());
				assertThat(m.getDescription()).isEqualTo(menu.getDescription());
				assertThat(m.getThumbNail()).isEqualTo(menu.getThumbNail());
				assertThat(m.getDisabled()).isEqualTo(menu.getDisabled());
			}
		}));
	}
}
