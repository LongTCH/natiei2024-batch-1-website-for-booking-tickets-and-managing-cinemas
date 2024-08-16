package cinemas.controllers;

import cinemas.models.Showtime;
import cinemas.models.ShowtimeSeat;
import cinemas.models.User;
import cinemas.services.ShowtimeSeatsService;
import cinemas.services.ShowtimesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TicketBookingsController {
    @Autowired
    private ShowtimesService showtimesService;
    @Autowired
    private ShowtimeSeatsService showtimeSeatsService;
    @GetMapping("/select-seats")
    public String seatBooking(@RequestParam("showtimeId") Integer showtimeId, Model model) {
        Showtime showtime = showtimesService.findById(showtimeId);
        var seatGrid = showtimeSeatsService.getSeatsGridByShowtime(showtimeId);
        model.addAttribute("showtime", showtime);
        model.addAttribute("seatGrid", seatGrid);
        model.addAttribute("screenColumns", seatGrid[0].length); // Number of columns
        model.addAttribute("screenRows", seatGrid.length); // Number of rows
        return "user/bookings/index";
    }
    @PostMapping("/select-seats")
    public String postBooking(@RequestParam(value = "seatIds", required = false)Integer[] selectedId,@RequestParam("showtimeId") Integer showtimeId, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        List<ShowtimeSeat> showtimeSeatList = showtimeSeatsService.getShowtimeseats(showtimeId, userId, selectedId);
        model.addAttribute("showtimeSeats", showtimeSeatList);
        return "user/foods/index";
    }
}
