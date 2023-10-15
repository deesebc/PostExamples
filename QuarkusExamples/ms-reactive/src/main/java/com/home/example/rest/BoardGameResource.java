package com.home.example.rest;

import java.util.List;

import com.home.example.entity.BoardGame;
import com.home.example.mapper.BoardGameMapper;
import com.home.example.pojo.Success;

import io.netty.util.internal.StringUtil;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import io.quarkus.panache.common.Sort.Direction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import io.quarkus.logging.Log;

@Path("/boardgame")
public class BoardGameResource {

    @Inject
    BoardGameMapper mapper;

    //warning: if we dont add quarkus-resteasy-reactive-jackson dependency, we can not produces or consumes JSON
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<BoardGame>> getAllSorted(@QueryParam("sortedBy") final String sortedBy, @QueryParam("order") final Direction order) {
        Log.info(String.format("getAllSorted: sortedBy %s order %s", sortedBy, order));
        Uni<List<BoardGame>> retorno;
        if (!StringUtil.isNullOrEmpty(sortedBy) && !StringUtil.isNullOrEmpty(sortedBy)) {
            retorno = BoardGame.listAll(Sort.by(sortedBy, order));
        } else if (!StringUtil.isNullOrEmpty(sortedBy)) {
            retorno = BoardGame.listAll(Sort.by(sortedBy));
        } else {
            retorno = BoardGame.listAll();
        }
        return retorno;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<BoardGame> getBoardGameById(@PathParam("id") final Long id) {
        return BoardGame.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @WithTransaction
    public Uni<Success> deleteById(@PathParam("id") final Long id) {
        return BoardGame.deleteById(id).onItem().transform((b) -> new Success(Boolean.toString(b)));       
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @WithTransaction
    public Uni<BoardGame> update(final BoardGame newBoardGame) {
        Log.info("update. "+newBoardGame); 

        BoardGame.update("designer = ?1 and name = ?2 where id = ?3", newBoardGame.designer, newBoardGame.name, newBoardGame.id);
        // BoardGame stored = BoardGame.findById(newBoardGame.id);
        // mapper.update(stored, newBoardGame);
        // BoardGame.persist(stored);
        // return stored;
        // Uni<BoardGame> h =  BoardGame.findById(newBoardGame.id)
        //     .log()
        //     .onItem().transform(stored -> mapper.copy(newBoardGame))
        //     .log()
        //     .onItem().invoke(updated -> updated.persistAndFlush())
        //     .log();
        // Uni<BoardGame> h =  BoardGame.findById(newBoardGame.id)
        // .log()
        // .onItem().transform(stored -> mapper.copy(newBoardGame));
        // h.onItem().invoke(updated -> updated.persistAndFlush());
        return Uni.createFrom().item(newBoardGame);
    }

    //warning: if we don put @WithTransaction generates an error
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @WithTransaction
    public Uni<BoardGame> persist(final BoardGame newBoardGame) {
        //return BoardGame.persist(newBoardGame);
        return newBoardGame.persist();
    }
}
