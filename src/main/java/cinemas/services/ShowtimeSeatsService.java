package cinemas.services;

import cinemas.dtos.ShowtimeSeatDto;
import cinemas.enums.SeatTypeEnum;
import cinemas.models.Screen;
import cinemas.models.Seat;
import cinemas.models.ShowtimeSeat;

import java.util.List;

public interface ShowtimeSeatsService {
    ShowtimeSeatDto[][] getSeatsGridByShowtime(Integer showtimeId);
    List<ShowtimeSeat> getShowtimeseats(Integer showtimeId, int userId, Integer[] selectedSeatIds);
}
