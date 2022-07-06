package com.deusley.novoProjeto.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	
	public static String decodeParam(String a) {
	 try {
		return	URLDecoder.decode(a,"UTF-8");
	} catch (UnsupportedEncodingException e) {
		return "";
	}
	}

	public static List<Integer> decodeIntList(String a){
		String[] vet = a.split(",");
		List<Integer> list = new ArrayList<>();
		
		for (int i=0; i<vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
	}
	
	
}












//Classe auxiliar convert//