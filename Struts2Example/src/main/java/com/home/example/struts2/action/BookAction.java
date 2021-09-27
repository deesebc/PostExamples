package com.home.example.struts2.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.home.example.struts2.pojo.Book;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BookAction extends GridPageAction {

	private static final long serialVersionUID = -2206764495660083184L;

	@Getter
	@Setter
	private String searchName;

	@Getter
	@Setter
	private String searchAuthor;

	@Getter
	@Setter
	List<Book> gridModel = new ArrayList<>();

	public String search() {
		log.info(String.format("BookAction - search. Name: %s. Author: %s.", searchName, searchAuthor));
		return SUCCESS;
	}

	public String searchJson() {
		log.info(String.format("BookAction - searchJson. Name: %s. Author: %s.", searchName, searchAuthor));
		if (StringUtils.isNotBlank(searchAuthor) && searchAuthor.contains("Asimov")) {
			gridModel.add(new Book(1, "Isaac Asimov", "Foundation I"));
			gridModel.add(new Book(1, "Isaac Asimov", "Foundation II"));
			gridModel.add(new Book(1, "Isaac Asimov", "Foundation III"));
		} else {
			gridModel = new ArrayList<>();
		}
		log.info("list Size: " + gridModel.size());
		return SUCCESS;
	}
}
