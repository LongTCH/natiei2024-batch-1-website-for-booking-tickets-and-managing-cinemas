package cinemas.services.impl;

import cinemas.dtos.ShowtimeSeatDto;
import cinemas.enums.SeatTypeEnum;
import cinemas.models.*;
import cinemas.repositories.SeatsRepository;
import cinemas.repositories.ShowtimeSeatsRepository;
import cinemas.repositories.ShowtimesRepository;
import cinemas.repositories.UsersRepository;
import cinemas.services.SeatsService;
import cinemas.services.ShowtimeSeatsService;
import cinemas.services.ShowtimesService;
import cinemas.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ShowtimeSeatsServiceImpl implements ShowtimeSeatsService {
    private ShowtimeSeatsRepository showtimeSeatsRepository;
    private ShowtimesRepository showtimesRepository;
    private UsersRepository usersRepository;
    private SeatsRepository seatsRepository;

    public ShowtimeSeatsServiceImpl(ShowtimeSeatsRepository showtimeSeatsRepository,
                                    ShowtimesRepository showtimesRepository,
                                    UsersRepository usersRepository,
                                    SeatsRepository seatsRepository){
        this.showtimeSeatsRepository = showtimeSeatsRepository;
        this.showtimesRepository = showtimesRepository;
        this.usersRepository = usersRepository;
        this.seatsRepository = seatsRepository;
    }
    @Override
    public ShowtimeSeatDto[][] getSeatsGridByShowtime(Integer showtimeId) {
        Showtime showtime = showtimesRepository.findById(showtimeId).orElse(null);
        Screen screen = showtime.getScreen();
        Set<Seat> seats = screen.getSeats();
        ShowtimeSeatDto[][] seatGrid = new ShowtimeSeatDto[screen.getVerticalSize()][screen.getHorizontalSize()];

        for (Seat seat : seats) {
            ShowtimeSeatDto showtimeSeatDto = new ShowtimeSeatDto();
            int row = seat.getVerticalIndex();
            int col = seat.getHorizontalIndex();
            showtimeSeatDto.setId(seat.getId());
            showtimeSeatDto.setName(seat.getName());
            showtimeSeatDto.setAvailable(seat.getAvailable());
            showtimeSeatDto.setType(String.valueOf(seat.getType()));
            showtimeSeatDto.setSelected(false);
            if(seat.getType() == SeatTypeEnum.STANDARD){
                showtimeSeatDto.setPrice(showtime.getPriceStandard());
            }else{
                showtimeSeatDto.setPrice(showtime.getPriceVip());
            }
            seatGrid[row][col] = showtimeSeatDto;
        }
        for (ShowtimeSeat showtimeSeat : showtime.getShowtimeSeats()){
            Seat seat = showtimeSeat.getSeat();
            int row = seat.getVerticalIndex();
            int col = seat.getHorizontalIndex();
            seatGrid[row][col].setSelected(true);
        }
        return seatGrid;
    }
    @Override
    public List<ShowtimeSeat> getShowtimeseats(Integer showtimeId, int userId, Integer[] selectedSeatIds){
        Showtime showtime = showtimesRepository.findById(showtimeId).orElse(null);
        User user = usersRepository.findById(userId).orElse(null);
        List<ShowtimeSeat> showtimeSeats = new ArrayList<>();
        for (Integer seatId : selectedSeatIds) {
            Seat seat = seatsRepository.findById(seatId).orElse(null);
            ShowtimeSeat showtimeSeat = new ShowtimeSeat();
            showtimeSeat.setShowtime(showtime);
            showtimeSeat.setSeat(seat);
            showtimeSeat.setUser(user);

            showtimeSeats.add(showtimeSeat);
        }
        return showtimeSeats;
    }
}
