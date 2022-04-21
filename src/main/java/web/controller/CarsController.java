package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class CarsController {

    @GetMapping(value = "/cars")
    public String printCars(ModelMap model, @RequestParam(value = "count", required = false) String count) {

        int carsCount = 0;
        if (!Objects.equals(count, "") && count != null) {
            try {
                carsCount = Integer.valueOf(count);
            } catch (NumberFormatException ex) {
                carsCount = 0;
            }
        }
        //HttpServletRequest request
        //String count = request.getParameter("count");

        List<Car> cars = new ArrayList<>();
        cars.add(new Car("BMW","X5","Black"));
        cars.add(new Car("Ford","Focus","White"));
        cars.add(new Car("Lada","Largus","Silver"));
        cars.add(new Car("Ford","C-max","Silver"));
        cars.add(new Car("Hynday","Grand Starex","Black"));


        model.addAttribute("cars", cars.subList(0,(carsCount > 0 && carsCount < 5)?carsCount:5));
        return "cars";
    }
}






