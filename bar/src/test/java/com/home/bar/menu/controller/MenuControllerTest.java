package com.home.bar.menu.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.home.bar.common.response.BaseResponse;
import com.home.bar.menu.MenuTest;
import com.home.bar.menu.dto.MenuDto;

public class MenuControllerTest extends MenuTest {

	@Test
	public void get() throws Exception {

		BaseResponse response = new BaseResponse();
		response.setStatus(HttpStatus.OK);
		response.setData(menuService.getMenuList());

		String list = mapper.writeValueAsString(response);

		mockMvc.perform(MockMvcRequestBuilders.get(PRE_FIX + DOMAIN)).andExpect(content().json(list));
	}

	@Test
	public void save() throws JsonProcessingException, Exception {

		BaseResponse response = new BaseResponse();
		response.setStatus(HttpStatus.OK);

		String list = mapper.writeValueAsString(response);

		// given
		MenuDto menuDto = MenuDto.builder().name("신메뉴").description("신 메뉴").disabled(0).build();
		mockMenuList.add(menuDto);

		// when then
		mockMvc.perform(MockMvcRequestBuilders.post(PRE_FIX + DOMAIN).content(mapper.writeValueAsString(menuDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		compare(menuService.getMenuList(), mockMenuList);
	}

	@Test
	public void update() throws JsonProcessingException, Exception {

		// given
		MenuDto updateDto = menuService.getMenuList().get(0);
		updateDto.setName("변경된 메뉴");

		mockMenuList.remove(0);
		mockMenuList.add(0, updateDto);

		// when
		mockMvc.perform(MockMvcRequestBuilders.put(PRE_FIX + DOMAIN).content(mapper.writeValueAsString(updateDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		//then
		compare(menuService.getMenuList(), mockMenuList);
	}

	@Test
	public void delete() throws Exception {
		
		// given
		MenuDto updateDto = menuService.getMenuList().get(0);
		updateDto.setDisabled(1);
		
		// when
		mockMvc.perform(MockMvcRequestBuilders.delete(PRE_FIX + DOMAIN).param("id", updateDto.getId().toString())).andExpect(status().isOk());

		// then
		MenuDto dto = menuService.getMenuList().get(0);
		assertThat(dto.getDisabled()).isEqualTo(updateDto.getDisabled());

	}
}