package cinemas.services.impl;

import cinemas.models.Seat;
import cinemas.repositories.SeatsRepository;
import cinemas.services.SeatsService;

public class SeatsServiceImpl implements SeatsService {
    private SeatsRepository seatsRepository;
    public SeatsServiceImpl(SeatsRepository seatsRepository){this.seatsRepository = seatsRepository;}
    @Override
    public Seat findById(int id) {
        return seatsRepository.findById(id).orElse(null);
    }
}
