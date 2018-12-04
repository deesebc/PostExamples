package test.es.home.example.testing.powermock;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import es.home.example.testing.controller.BookController;
import es.home.example.testing.pojo.Book;
import es.home.example.testing.ws.client.BookWSClient;

@RunWith(PowerMockRunner.class)
public class WhiteboxExample {
	@Mock
	private BookWSClient client;
	private BookController controller;

	@Test
	public void getBookByStaticAttribute() throws RemoteException {
		Mockito.when(client.getBook(1)).thenReturn(new Book("name", "author"));
		Book book = controller.getBookByStaticAttribute(1);
		assertEquals("name", book.getName());
		Mockito.verify(client, Mockito.times(1)).getBook(1);
	}

	@Before
	public void setUp() throws Exception {
		Whitebox.setInternalState(BookController.class, "client", client);
		controller = new BookController();
	}
}
