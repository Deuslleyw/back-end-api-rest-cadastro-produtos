package com.deusley.novoProjeto.services.validation.utils;

public class BRZ {

	
	private static final int[] weigtSsn = {11,10,9,8,7,6,5,4,3,2};    //<<<CPF <<<<<
	
	
	private static final int[] weigtTin = {6,5,4,3,2,9,8,7,6,5,4,3,2};  //< CNPJ<<<
	
	private static int calculate(final String str, final int[] weight) {
		
		int sum = 0;
		for(int i = str.length() -1, digit; i >=0; i--) {
			digit = Integer.parseInt(str.substring(i, i + 1));
			sum += digit * weight[weight.length - str.length()+ i];          
		}
		sum = 11 - sum % 11;
		return sum > 9 ? 0 : sum;
	}                                                                                //ValidaCPF<<
	
	public static boolean isValidCPF(final String pf) {
		if((pf ==null) || (pf.length() != 11) || pf.matches(pf.charAt(0)+ "{11}")) return false;
		
		final Integer digit1 = calculate(pf.substring(0,9), weigtSsn);
		final Integer digit2 = calculate(pf.substring(0,9)+ digit1, weigtSsn);
		
		return pf.equals(pf.substring(0,9) + digit1.toString()+ digit2.toString());
	}
	                                                                                  //ValidaPJ<<
	public static boolean isValidCNPJ(final String pj) {
		if((pj ==null) || (pj.length() != 14) || pj.matches(pj.charAt(0)+ "{14}")) return false;
		
		final Integer digit1 = calculate(pj.substring(0,12), weigtTin);
		final Integer digit2 = calculate(pj.substring(0,12)+ digit1, weigtTin);
		
		return pj.equals(pj.substring(0,12) + digit1.toString()+ digit2.toString());
	
	}
}
