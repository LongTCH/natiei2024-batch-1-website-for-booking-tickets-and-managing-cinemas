package cinemas.services;

import cinemas.models.Seat;

public interface SeatsService {
    Seat findById(int id);
}
