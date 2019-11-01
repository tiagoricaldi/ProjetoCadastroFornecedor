/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Tiago
 * Created: 30/10/2019
 */

CREATE TABLE public.fornecedor
(
  id_fornecedor bigint NOT NULL,
  nome character varying(150),
  email character varying(150),
  cnpj character varying(150),
  comentario character varying(150),
  CONSTRAINT fornecedor_pkey PRIMARY KEY (id_fornecedor)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.fornecedor
  OWNER TO postgres;



