package model.parsers;

import model.RequestList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import exceptions.BadFormattedRequestException;
import globals.Constants.FileNames;

/**
 * The class responsible for parsing the requests file and generating a RequestList.
 * // IMPORTANTE una mascara horaria pot incloure com a maxim 5 franges horaries
 * 
 * @author Jendoliver
 * @author Irene
 *
 */
public class ParserRequests extends Parser 
{
	private List<String> loadedRequests;
	private RequestList acceptedRequests = new RequestList();
	
	public ParserRequests(FileNames fileToParse) {
		loadRequests(fileToParse.getName());
	}
	
	public ParserRequests(List<String> stringsToParse) {
		loadedRequests = stringsToParse;
	}
	
	public RequestList parse() 
	{
		for(String request : loadedRequests)
		{
			try {
				parse(request);
			} catch (Exception e) {
				// TODO log message
			}
		}
		return acceptedRequests;
	}
	
	private void parse(String request) throws BadFormattedRequestException
	{
		/*
		 * COPIAR AQUI TODA LA LOGICA DE DENTRO DEL BUCLE
		 * QUE ITERA POR EL FICHERO UTILIZANDO EL ARGUMENTO
		 * request COMO SI FUERA EL RESULTADO DE br.readLine()
		 * 
		 * --- A CAMBIAR ---
		 *  - Lanzar las excepciones sin hacer catch aqui, solo throw con la razon indicada
		 *  -- (si hace falta throwear mas tipos de exceptiones rollo IOException, NumberFormat etc se van añadiendo en la firma)
		 *  - Meter las requests buenas en acceptedRequests (campo de la clase)
		 */
	}
	
	private void loadRequests(String fileToParse)
	{
		try (BufferedReader br = new BufferedReader(new FileReader(fileToParse)))
	    {
	        String sCurrentLine;
	        while ((sCurrentLine = br.readLine()) != null) 
	        {
	            loadedRequests.add(sCurrentLine);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
