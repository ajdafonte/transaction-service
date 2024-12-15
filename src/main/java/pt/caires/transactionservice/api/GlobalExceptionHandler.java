package pt.caires.transactionservice.api;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletResponse;
import pt.caires.transactionservice.domain.exception.NotFoundServiceException;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(IllegalArgumentException.class)
  public void handleIllegalArgumentException(IllegalArgumentException exception,
      HttpServletResponse response) throws IOException {
    LOGGER.warn("Illegal argument exception", exception);
    response.sendError(HTTP_BAD_REQUEST, exception.getMessage());
  }

  @ExceptionHandler(NotFoundServiceException.class)
  public void handleNotFoundServiceException(NotFoundServiceException exception,
      HttpServletResponse response) throws IOException {
    LOGGER.warn("Not found exception", exception);
    response.sendError(HTTP_NOT_FOUND, exception.getMessage());
  }

}
