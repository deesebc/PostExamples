package es.home.example.pojo;

import java.util.ArrayList;
import java.util.List;

public class Library {
	List<Book> library = new ArrayList<>();

	public List<Book> getLibrary() {
		return library;
	}

	public void setLibrary(List<Book> library) {
		this.library = library;
	}
	
	
}
