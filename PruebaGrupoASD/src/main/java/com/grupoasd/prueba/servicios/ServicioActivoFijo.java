/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupoasd.prueba.servicios;

import com.grupoasd.prueba.dao.ActivoFijoDAO;
import com.grupoasd.prueba.modelo.ActivoFijo;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;
/**
 *
 * @author Santiago
 */
@Path("activo")
public class ServicioActivoFijo {
    
    ActivoFijoDAO activoDAO = new ActivoFijoDAO();
    
    @POST
    @Path("crear")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearActivo(String data){
        JSONObject jsonObj = new JSONObject(data);
        ActivoFijo activo = crearActivo(jsonObj);
        //System.out.println("Datos: "+jsonObj.getString("numInterno"));
        if(activoDAO.crearActivo(activo) == 1){
            return Response.status(Response.Status.CREATED).header("Creado", "El recurso ha sido creado").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).header("Solicitud incorrecta", "El recurso no pudo ser creado").build();       
        
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Object getActivos(){
        System.out.println("Get Activos");
        List<ActivoFijo> activos = activoDAO.getAllActivos();
        System.out.println("ActivoDAO: "+activos);
        
        if (activos != null)
            return Response.ok(activos).build();
        else
            return Response.status(Response.Status.NOT_FOUND);                
    }
    
    @GET
    @Path("buscar/tipo/{tipo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Object getActivoTipo(@PathParam("tipo") String tipo){
        
        List<ActivoFijo> activos = activoDAO.getActivosByTipo(tipo);        
        if (activos != null)
            return Response.ok(activos).build();
        else
            return Response.status(Response.Status.NOT_FOUND);  
    }
    
    @GET
    @Path("buscar/fecha/{fecha}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Object getActivoFecha(@PathParam("fecha") String fecha){
               
        List<ActivoFijo> activos = activoDAO.getActivosByFecha(fecha);        
        if (activos != null)
            return Response.ok(activos).build();
        else
            return Response.status(Response.Status.NOT_FOUND);  
    }
    
    @GET
    @Path("buscar/serial/{serial}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Object getActivoSeriañ(@PathParam("serial") String serial){
               
        List<ActivoFijo> activos = activoDAO.getActivosBySerial(serial);        
        if (activos != null)
            return Response.ok(activos).build();
        else
            return Response.status(Response.Status.NOT_FOUND);  
    }
    
    @PUT
    @Path("actualizar/serial/{serial}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Object actualizarSerial(@PathParam("serial") String serial){
        System.out.println("Serial: "+serial);        
        return null;
    }
    
    @PUT
    @Path("actualizar/fechaBaja/{fechaBaja}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Object actualizarFechaBaja(@PathParam("fechaBaja") String fechaBaja){
        System.out.println("Fecha Baja: "+fechaBaja);
        return null;
    }

    private ActivoFijo crearActivo(JSONObject jsonObj) {
        ActivoFijo activo = new ActivoFijo();
        activo.setNumInterno(jsonObj.getInt("numInterno"));
        activo.setSerial(jsonObj.getString("serial"));        
        activo.setNombre(jsonObj.getString("nombre"));
        activo.setDate(jsonObj.getString("fechaCompra"));
        System.out.println(jsonObj.getString("fechaCompra"));        
        activo.setValor(jsonObj.getDouble("valor"));
        activo.setDescripcion(jsonObj.getString("descripcion"));
        activo.setEstado(jsonObj.getInt("idEstado"));
        activo.setIdTipo(jsonObj.getInt("tipo"));
        return activo;
    }
    
}
