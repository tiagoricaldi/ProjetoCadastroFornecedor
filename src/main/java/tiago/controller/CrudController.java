/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiago.controller;

import java.sql.SQLException;
import tiago.model.Fornecedor;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tiago.dao.FornecedorDAO;

/**
 *
 * @author Tiago
 */
@Path("crudcontroller")
public class CrudController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Fornecedor> listFornecedores() {
        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            return fornecedorDAO.listar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CrudController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id_fornecedor}/")
    public Fornecedor getFornecedores(@PathParam("id_fornecedor") long id_fornecedor) {

        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            return fornecedorDAO.selecionar(id_fornecedor);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CrudController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response create(Fornecedor fornecedor) {
        try {

            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            fornecedorDAO.inserir(fornecedor);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CrudController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response update(Fornecedor fornecedor) {
        try {

            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            fornecedorDAO.alterar(fornecedor);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CrudController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id_fornecedor}/")
    public Response delete(@PathParam("id_fornecedor") long id_fornecedor) {
        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            fornecedorDAO.excluir(id_fornecedor);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CrudController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
