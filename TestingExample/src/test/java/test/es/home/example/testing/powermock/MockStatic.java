package test.es.home.example.testing.powermock;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import es.home.example.testing.controller.BookController;
import es.home.example.testing.pojo.Book;
import es.home.example.testing.ws.helper.BookWSHelper;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BookWSHelper.class) // class with static method
public class MockStatic {
	private BookController controller;

	@Test
	public void getBookByStaticClass() throws RemoteException {
		controller = new BookController();
		// PowerMockito.mockStatic(BookWSHelper.class);
		Mockito.when(BookWSHelper.getBook(1)).thenReturn(new Book("name", "author"));
		Book book = controller.getBookByStaticClass(1);
		assertEquals("name", book.getName());
	}
}
