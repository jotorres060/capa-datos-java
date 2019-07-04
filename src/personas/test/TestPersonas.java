package personas.test;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import personas.dto.PersonaDTO;
import personas.jdbc.PersonaDAO;
import personas.jdbc.PersonaDaoJDBC;

public class TestPersonas {

	public static void main(String[] args) {
		PersonaDAO personaDao = new PersonaDaoJDBC();
		
		PersonaDTO persona = new PersonaDTO();
		persona.setNombre("Scott");
		persona.setApellido("Terry");
		
		PersonaDTO persona2 = new PersonaDTO();
		persona2.setNombre("Maria");
		persona2.setApellido("Young");
		
		try {
			//personaDao.insert(persona);
			//personaDao.insert(persona2);
			
			PersonaDTO personaTmp = new PersonaDTO();
			//personaTmp.setId(1);
			//personaTmp.setNombre("Scott B.");
			//personaTmp.setApellido("Terry C.");
			
			//personaDao.update(personaTmp);
			
			//personaTmp.setId(2);
			
			//personaDao.delete(personaTmp);
			
			List<PersonaDTO> personas = personaDao.select();
			
			for (PersonaDTO p : personas) {
				System.out.println(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
