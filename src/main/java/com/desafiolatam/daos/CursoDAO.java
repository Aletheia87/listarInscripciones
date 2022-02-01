package com.desafiolatam.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.desafiolatam.entidades.CursoDTO;

public class CursoDAO {

	public List obtieneCursos() throws SQLException, ClassNotFoundException {

		// creamos la lista de objetos que devolveran los resultados
		List<CursoDTO> listaDeCursos = new ArrayList<CursoDTO>();

		// creamos la consulta a la base de datos
		String consultaSql = " SELECT id_curso, descripcion, precio " + " FROM curso ";

		// conexion a la base de datos y ejecucion de la sentencia
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/listar_inscripciones?serverTimezone=UTC", "root", "Archie.2628");

		try (PreparedStatement stmt = conexion.prepareStatement(consultaSql)) {

			ResultSet resultado = stmt.executeQuery();
			while (resultado.next()) {
				CursoDTO cursoDto = new CursoDTO();
				cursoDto.setIdCurso(resultado.getInt("id_curso"));
				cursoDto.setDescripcion(resultado.getString("descripcion"));
				cursoDto.setPrecio(resultado.getDouble("precio"));
				listaDeCursos.add(cursoDto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listaDeCursos;
	}

}