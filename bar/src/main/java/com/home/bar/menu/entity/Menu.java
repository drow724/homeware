package com.home.bar.menu.entity;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.home.bar.common.entity.BaseEntity;
import com.home.bar.menu.dto.MenuDto;

import lombok.Getter;

@Entity
@Getter
public class Menu extends BaseEntity {

	@Id
	@GeneratedValue
	private Long	id;
	
	private String	name;
	
	private File	thumbNail;
	
	private String	description;
	
	private int		disabled;
	
	public void disable() {
		this.disabled = 1;
	}
	
	public void transfer(MenuDto dto) {
		this.name			= 	dto.getName();
		this.thumbNail		=	dto.getThumbNail();
		this.description	=	dto.getDescription();
		this.disabled		=	dto.getDisabled();
	}
}
