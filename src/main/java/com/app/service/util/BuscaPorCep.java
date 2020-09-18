package com.app.service.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.entity.Endereco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class BuscaPorCep implements Serializable{
	private static final long serialVersionUID = 1L;

	private final static Logger logger = LoggerFactory.getLogger(BuscaPorCep.class);
	
    public static Endereco buscarCep(String cepBuscar) {
      
    	cepBuscar = cepBuscar.replace(".","").replace("-", "").replace(" ", "").trim();
    	
        if(cepBuscar.length() != 8) {
        	logger.info("ERRO DE CEP, QUANTIDADE DE DIGITOS NAO CONFERE, QUANTIDADE DE CARACTER: "+cepBuscar.length());
        	return null;
        }
        
        
    	try {
    		
			URL urlJson = new URL("http://viacep.com.br/ws/"+ cepBuscar +"/json");
			URLConnection connection = urlJson.openConnection();

            int response = ((HttpURLConnection) connection).getResponseCode();
            logger.info("RESPOSTA HTTP: " + response);
            
            if(response != 200) {
            	logger.info("ERRO DE RETORNO HTTP");
            	return null;
            }
            
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
    	
            String inputLine;
            StringBuffer buffer = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
               buffer.append(inputLine);
            }
            
            in.close();
            
            final Gson gson = new GsonBuilder().create();
            
            JsonObject  json = gson.fromJson(buffer.toString(), JsonObject.class);
            
           
            String cep 			= (json.get("cep") 			!= null) ? json.get("cep").toString().replace("\"", "") 		: null;
            String logradouro 	= (json.get("logradouro") 	!= null) ? json.get("logradouro").toString().replace("\"", "") 	: null;
            String complemento 	= (json.get("complemento") 	!= null) ? json.get("complemento").toString().replace("\"", "") : null;
            String bairro 		= (json.get("bairro") 		!= null) ? json.get("bairro").toString().replace("\"", "") 		: null;
            String localidade 	= (json.get("localidade") 	!= null) ? json.get("localidade").toString().replace("\"", "") 	: null;
            String uf 			= (json.get("uf") 			!= null) ? json.get("uf").toString().replace("\"", "") 			: null;
            String unidade 		= (json.get("unidade") 		!= null) ? json.get("unidade").toString().replace("\"", "") 	: null;
            String ibge 		= (json.get("ibge") 		!= null) ? json.get("ibge").toString().replace("\"", "") 		: null;
            String gia 			= (json.get("gia") 			!= null) ? json.get("gia").toString().replace("\"", "")  		: null; // Guia de Informa��o e Apura��o do ICMS � uma declara��o mensal, exigida na forma da legisla��o, cujas informa��es devem refletir a escritura��o efetuada no Livro Fiscal Registro de Apura��o do ICMS
            
            Endereco endereco = new Endereco(null, cep, logradouro, null, complemento, bairro, localidade, uf, unidade, ibge, gia);
            logger.info(endereco.toString());
            return endereco;
          
            
    	} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

		

	}

	public static void main(String[] args) {
		buscarCep("00725050");
	}
}
