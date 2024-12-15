package pt.caires.transactionservice.api;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(IllegalArgumentException.class)
  public void handleIllegalArgumentException(IllegalArgumentException exception,
      HttpServletResponse response) throws IOException {
    LOGGER.warn("Illegal argument exception", exception);
    response.sendError(HTTP_BAD_REQUEST, exception.getMessage());
  }

}
