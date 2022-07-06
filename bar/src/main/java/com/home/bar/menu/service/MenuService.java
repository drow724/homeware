package com.home.bar.menu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.home.bar.menu.dto.MenuDto;
import com.home.bar.menu.entity.Menu;
import com.home.bar.menu.repository.MenuRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

	private final MenuRepository menuRepository;

	public List<MenuDto> getMenuList() {
		return menuRepository.findAll().stream().map(
				m -> MenuDto.builder()
							.id(m.getId())
							.name(m.getName())
							.description(m.getDescription())
							.disabled(m.getDisabled())
							.build()
				).collect(Collectors.toList());
	}

	public void save(MenuDto menuDto) {
		
		Menu menu = new Menu();
		menu.transfer(menuDto);
		
		menuRepository.save(menu);
	}

	public void update(MenuDto menuDto) {
		menuRepository.findById(menuDto.getId()).orElseThrow().transfer(menuDto);
	}

	public void delete(Long id) {
		menuRepository.findById(id).orElseThrow().disable();
	}
}
