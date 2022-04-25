package io.swagger.aspectJ;

import io.swagger.model.Order;
import io.swagger.model.Place;
import io.swagger.service.OrderService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;


@Aspect
@Component
public class OrderAspect {
    private static Logger logger = LoggerFactory.getLogger(OrderAspect.class);

    @Autowired
    OrderService orderService;

    @Pointcut("execution(* io.swagger.api.OrderApiController..*(..))")
    public void controller() {
    }

    @Pointcut("execution(* *.*(..)) ")
    protected void allMethod() {
    }

    @Before("controller()&& allMethod()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        logger.info("BEFORE");
    }
    @After("controller()&& allMethod() && args(body)")
    public void logAfter(JoinPoint joinPoint, Order body) {
        Map<String, Integer> names = new HashMap<>();
        int max = 0;
        String bestguy = "";
        String mail;
        for (Order order : orderService.findAllOrders()) {
            mail = order.getMail();
            if (names.containsKey(mail)) {
                int i = names.get(mail);
                names.put(mail, i+1);
            }
            else {
                names.put(mail, 1);
            }

            if (names.get(mail) > max) {
                max = names.get(mail);
                bestguy = mail;
            }
        }

        try(FileWriter writer = new FileWriter("src/main/resources/static/STATISTIC/orders.txt", true)) {
            writer.write("NEW ORDER: ");
            writer.append('\n');
            writer.write("place: " + body.getPlace().getId() + ", ");
            writer.write("price: " + body.getPlace().getPrice());
            writer.append('\n');
            writer.write("dates: " + body.getBookingDateStart() + " - " + body.getBookingDateEnd());
            writer.append('\n');
            writer.write("contact: " + body.getName() + ", " + body.getMail());
            writer.append('\n');
            writer.append('\n');

            writer.write("THE CUSTOMER OF THE MONTH: " + bestguy);
            writer.append('\n');

            writer.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.info("AFTER");
    }
}
