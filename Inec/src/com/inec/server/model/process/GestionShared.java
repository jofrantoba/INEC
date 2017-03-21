package com.inec.server.model.process;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

public class GestionShared {
			
	public static void closeConnection(PersistenceManager pm, Transaction tx){
		if(!pm.isClosed()){
			if(tx!=null){
				if(tx.isActive()){
					tx.rollback();
				}			
			}
			pm.close();							
		}	
	}
}
