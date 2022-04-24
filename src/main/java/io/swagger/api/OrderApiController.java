package io.swagger.api;

import io.swagger.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.threeten.bp.LocalDate;
import io.swagger.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-03-23T09:25:02.102Z[GMT]")
@RestController
public class OrderApiController implements OrderApi {

    @Autowired
    private OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(OrderApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public OrderApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Order>> getYachtPlaces(@NotNull @Pattern(regexp="([0-9]{2})-(?:[0-9]{2})-([0-9]{4})") @Size(min=10,max=10) @Parameter(in = ParameterIn.QUERY, description = "**Date**. *Example: 01-01-2022*. List of yacht parking places by date" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "date_start", required = true) LocalDate dateStart,@NotNull @Pattern(regexp="([0-9]{2})-(?:[0-9]{2})-([0-9]{4})") @Size(min=10,max=10) @Parameter(in = ParameterIn.QUERY, description = "**Date**. *Example: 01-01-2022*. List of yacht parking places by date" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "date_end", required = true) LocalDate dateEnd) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return ResponseEntity.ok(orderService.findAllOrders());
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Order>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Order>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Order> postOrder(@Parameter(in = ParameterIn.DEFAULT, description = "book place for the yacht", required=true, schema=@Schema()) @Valid @RequestBody Order body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                System.out.println(body.getBookingDateStart());
                System.out.println(body.getBookingDateEnd());
                return ResponseEntity.ok(orderService.addOrder(body));
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        System.out.println(body);
        log.error("Couldn't serialize response for content type application/json");
        return new ResponseEntity<Order>(HttpStatus.NOT_IMPLEMENTED);
    }

}
