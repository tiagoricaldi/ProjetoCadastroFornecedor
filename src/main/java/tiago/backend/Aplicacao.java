/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiago.backend;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Tiago
 */
@ApplicationPath("rest")
public class Aplicacao extends ResourceConfig {

    public Aplicacao() {
        packages("tiago.controller");
    }

}
