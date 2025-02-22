package clinica.model.payload;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MensajeResponse implements Serializable{
	private static final long serialVersionUID = 5L;
	
	private String mensaje;
	private Object objeto;
}
