package es.home.example.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.wso2.carbon.metrics.core.annotation.Counted;
import org.wso2.carbon.metrics.core.annotation.Timed;
import org.wso2.msf4j.Request;
import org.wso2.msf4j.interceptor.annotation.RequestInterceptor;
import org.wso2.msf4j.security.oauth2.OAuth2SecurityInterceptor;

import es.home.example.dao.BookDao;
import es.home.example.pojo.Book;
import es.home.example.service.BookService;

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
@RequestInterceptor(OAuth2SecurityInterceptor.class)
public class BookServiceImpl implements BookService {

    private BookDao dao;

    public BookServiceImpl() {
        System.out.println("BookServiceImpl");
    }

    public BookServiceImpl(final BookDao dao) {
        System.out.println("BookServiceImpl with dao");
        this.dao = dao;
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy");
    }

    @Override
    @GET
    @Path("/{bookId}")
    @Timed
    public Book getBookById(@PathParam("bookId") final Integer bookId) {
        return dao.findById(bookId);
    }

    @Override
    @GET
    @Path("/list")
    @Counted(name = "getList", monotonic = true)
    public List<Book> getList(@Context final Request request) {
        return dao.getResultList();
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
        System.out.println("deploymentYamlPath: " + System.getProperty("msf4j.conf"));
    }
}
