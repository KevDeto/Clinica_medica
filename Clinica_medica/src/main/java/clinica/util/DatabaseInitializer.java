package clinica.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

	@Autowired
	private DataSource dataSource;

	@EventListener(ApplicationReadyEvent.class)
	public void initializeDatabase() {
		try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {

			String alterQuery = "ALTER TABLE turno " + "MODIFY COLUMN hora_inicio TIME(0), "
					+ "MODIFY COLUMN hora_fin TIME(0)";
			statement.execute(alterQuery);
		} catch (SQLException ex) {
			throw new RuntimeException("Error ejecutando consulta SQL para modificar la tabla 'turno_disponible'", ex);
		}
	}
}
