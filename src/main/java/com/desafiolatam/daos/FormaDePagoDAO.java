package com.desafiolatam.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.desafiolatam.entidades.FormaDePagoDTO;

public class FormaDePagoDAO {

public List obtieneFormasDePago() throws SQLException, ClassNotFoundException {
		
		//creamos la lista de objetos que devolveran los resultados
		List<FormaDePagoDTO> formaDePago = new ArrayList<FormaDePagoDTO>();
		
		//creamos la consulta a la base de datos
		String consultaSql = " SELECT id_forma_pago, descripcion, recargo" 
				   		   + " FROM forma_pago ";
		
		//conexion a la base de datos y ejecucion de la sentencia
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection  conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/listar_inscripciones?serverTimezone=UTC", "root", "Archie.2628");
		
		try(PreparedStatement stmt = conexion.prepareStatement(consultaSql)){
	
			ResultSet resultado = stmt.executeQuery();
			while(resultado.next()) {
				FormaDePagoDTO formaPago = new FormaDePagoDTO();
				formaPago.setIdFormaDePago(resultado.getInt("id_forma_pago"));
				formaPago.setDescripcion(resultado.getString("descripcion"));
				formaPago.setRecargo(resultado.getString("recargo"));
				formaDePago.add(formaPago);
			}	
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return formaDePago;
	}

}
