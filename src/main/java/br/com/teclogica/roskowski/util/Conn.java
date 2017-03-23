package br.com.teclogica.roskowski.util;
 
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
@Stateful
public class Conn {

	@PersistenceContext(unitName = "lerigo")
	protected EntityManager em;  
	
}
