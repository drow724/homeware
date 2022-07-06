package com.home.bar.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.bar.menu.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
