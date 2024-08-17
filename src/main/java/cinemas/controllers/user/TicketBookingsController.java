package cinemas.controllers.user;

import cinemas.dtos.FoodSelectionDto;
import cinemas.dtos.FoodSelectionFormDto;
import cinemas.models.Food;
import cinemas.models.Showtime;
import cinemas.models.ShowtimeSeat;
import cinemas.models.User;
import cinemas.services.FoodsService;
import cinemas.services.ShowtimeSeatsService;
import cinemas.services.ShowtimesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/ticket-bookings")
public class TicketBookingsController {
    @Autowired
    private ShowtimesService showtimesService;
    @Autowired
    private ShowtimeSeatsService showtimeSeatsService;
    @Autowired
    private FoodsService foodsService;

    @GetMapping("/showtimes")
    public String show(@RequestParam("showtimeId") Integer showtimeId, Model model) {
        Showtime showtime = showtimesService.findById(showtimeId);
        var seatGrid = showtimeSeatsService.getSeatsGridByShowtime(showtimeId);
        model.addAttribute("showtime", showtime);
        model.addAttribute("seatGrid", seatGrid);
        model.addAttribute("screenColumns", seatGrid[0].length); // Number of columns
        model.addAttribute("screenRows", seatGrid.length); // Number of rows
        return "user/showtimes/show";
    }

    @PostMapping("/showtimes")
    public String showFoods(@RequestParam(value = "seatIds", required = false) Integer[] selectedId, @RequestParam("showtimeId") Integer showtimeId, HttpSession session, Model model) {
        List<Food> foods = foodsService.getAllFoods();
        var foodSlectionForm = new FoodSelectionFormDto();
        foods.forEach(food -> {
            var foodSelection = new FoodSelectionDto();
            foodSelection.setFood(food);
            foodSelection.setCount(0);
            foodSlectionForm.addFoodSelection(foodSelection);
        });
        Showtime showtime = showtimesService.findById(showtimeId);
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        List<ShowtimeSeat> showtimeSeatList = showtimeSeatsService.getShowtimeseats(showtimeId, userId, selectedId);
        Map<String, Integer> seatPrices = showtimeSeatsService.getSeatPrices(showtimeSeatList);
        model.addAttribute("foodSelectionForm", foodSlectionForm);
        model.addAttribute("showtime", showtime);
        model.addAttribute("seatPrices", seatPrices);
        model.addAttribute("showtimeSeats", showtimeSeatList);
        return "user/foods/index";
    }

    @PostMapping("showtimes/{id}/payments")
    public String showPayments(@RequestParam("id") Integer showtimeId, @ModelAttribute FoodSelectionFormDto foodSelectionFormDto, HttpSession session, Model model) {
        foodSelectionFormDto.getFoodSelections().forEach(foodSelectionDto -> {
            if (foodSelectionDto.getCount() > 0) {
                System.out.println(foodSelectionDto.getFood().getName() + " " + foodSelectionDto.getCount());
            }
        });
        return "user/payments/index";
    }
}