package com.home.bar.menu.dto;

import java.io.File;

import com.home.bar.menu.entity.Menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {

	private Long	id;
	
	private String	name;
	
	private File	thumbNail;
	
	private String	description;
	
	private int		disabled;
	
	public void transfer(Menu menu) {
		this.name			= 	menu.getName();
		this.thumbNail		=	menu.getThumbNail();
		this.description	=	menu.getDescription();
		this.disabled		=	menu.getDisabled();
	}
}
