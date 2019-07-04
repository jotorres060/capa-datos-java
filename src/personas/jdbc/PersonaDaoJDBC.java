package personas.jdbc;

import java.sql.*;
import java.util.*;
import personas.dto.PersonaDTO;

public class PersonaDaoJDBC implements PersonaDAO {
	private Connection userConn;
	private final String INSERT = "INSERT INTO personas (nombre, apellido) VALUES (?,?)";
	private final String UPDATE = "UPDATE personas SET nombre = ?, apellido = ? WHERE id = ?";
	private final String DELETE = "DELETE FROM personas WHERE id = ?";
	private final String SELECT = "SELECT id, nombre, apellido FROM personas";
	
	public PersonaDaoJDBC() {
	}
	
	public PersonaDaoJDBC(Connection conn) {
		this.userConn = conn;
	}
	
	@Override
	public int insert(PersonaDTO persona) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows = 0;
		
		try {
			conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
			stmt = conn.prepareStatement(INSERT);
			int index = 1;
			
			stmt.setString(index++, persona.getNombre());
			stmt.setString(index, persona.getApellido());
			rows = stmt.executeUpdate();
		} finally {
			Conexion.close(stmt);
			if (this.userConn == null) {
				Conexion.close(conn);
			}
		}
		return rows;
	}
	
	@Override
	public int update(PersonaDTO persona) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows;
		
		try {
			conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
			stmt = conn.prepareStatement(UPDATE);
			int index = 1;
			
			stmt.setString(index++, persona.getNombre());
			stmt.setString(index++, persona.getApellido());
			stmt.setInt(index, persona.getId());
			rows = stmt.executeUpdate();
		} finally {
			Conexion.close(stmt);
			if (this.userConn == null) {
				Conexion.close(conn);
			}
		}
		return rows;
	}
	
	@Override
	public int delete(PersonaDTO persona) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rows;
		
		try {
			conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
			stmt = conn.prepareStatement(DELETE);
			int index = 1;
			
			stmt.setInt(index, persona.getId());
			rows = stmt.executeUpdate();
		} finally {
			Conexion.close(stmt);
			if (this.userConn == null) {
				Conexion.close(conn);
			}
		}
		return rows;
	}
	
	@Override
	public List<PersonaDTO> select() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PersonaDTO personaDTO = null;
		List<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		
		try {
			conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
			stmt = conn.prepareStatement(SELECT);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				personaDTO = new PersonaDTO();
				personaDTO.setId(rs.getInt(1));
				personaDTO.setNombre(rs.getString(2));
				personaDTO.setApellido(rs.getString(3));
				
				personas.add(personaDTO);
			}
		} finally {
			Conexion.close(rs);
			Conexion.close(stmt);
			if (this.userConn == null) {
				Conexion.close(conn);
			}
		}
		return personas;
	}
}
