package com.inec.shared;

public class GeneradorClave {	
	public static String getPassword(String key1,String key2,String key3, int length) {
		String key= key1 + key2.toUpperCase()+ key3;
		String pswd = "";
		if(!key1.isEmpty()){
			pswd=key1.substring(0, 1);
		}else{
			if(!key2.isEmpty()){
				pswd=key2.substring(0, 1);
			}else{
				pswd="A";
			}
		}		
 
		for (int i = 0; i < length; i++) {
			char myChar=' ';
			do{
				myChar=(key.charAt((int)(Math.random() * key.length())));
			}while(myChar==' ');			
			pswd+=myChar;
		}
		return pswd;
	}
	
	public static Integer returnIndice(String dia){
		if(dia.equalsIgnoreCase("LUNES")){
			return 2;
		}else if(dia.equalsIgnoreCase("MARTES")){
			return 3;
		}else if(dia.equalsIgnoreCase("MIERCOLES")){
			return 4;
		}else if(dia.equalsIgnoreCase("JUEVES")){
			return 5;
		}else if(dia.equalsIgnoreCase("VIERNES")){
			return 6;
		}else if(dia.equalsIgnoreCase("SABADO")){
			return 7;
		}else if(dia.equalsIgnoreCase("DOMINGO")){
			return 8;
		}
		return -1;
	}
	
	
	public static Integer cambiarHora(Integer horaDatastore){
		switch (horaDatastore) {
		case 1:
			return 20;
		case 2:
			return 21;
		case 3:
			return 22;
		case 4:
			return 23;
		case 5:
			return 0;
		case 6:
			return 1;
		case 7:
			return 2;
		case 8:
			return 3;
		case 9:
			return 4;
		case 10:
			return 5;
		case 11:
			return 6;
		case 12:
			return 7;
		case 13:
			return 8;
		case 14:
			return 9;
		case 15:
			return 10;
		case 16:
			return 11;
		case 17:
			return 12;
		case 18:
			return 13;
		case 19:
			return 14;
		case 20:
			return 15;
		case 21:
			return 16;
		case 22:
			return 17;
		case 23:
			return 18;
		case 24:
			return 19;

		default:
			return -1;
		}
	}
}
