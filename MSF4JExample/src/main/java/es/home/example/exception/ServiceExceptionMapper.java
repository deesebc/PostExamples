package es.home.example.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ServiceExceptionMapper implements ExceptionMapper<Exception> {

	public class EntityReponse {
		private String message;

		public EntityReponse(final String message) {
			super();
			this.message = message;
		}

		@Override
		public String toString() {
			return "EntityReponse [message=" + message + "]";
		}

	}

	@Override
	public Response toResponse(final Exception exception) {
		System.out.println("ServiceExceptionMapper");
		return Response.ok(new EntityReponse("Internal server error"), MediaType.APPLICATION_JSON).build();
	}

}
