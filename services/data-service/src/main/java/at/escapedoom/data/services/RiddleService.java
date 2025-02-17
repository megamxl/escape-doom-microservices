package at.escapedoom.data.services;

import at.escapedoom.data.data.entity.Riddle;
import at.escapedoom.data.data.repository.RiddleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiddleService {

    private final RiddleRepository repository;

    public List<Riddle> getAll() {
        return repository.findAll();
    }

    public Riddle create(Riddle riddle)  {
        return repository.save(riddle);
    }

    public Riddle update(Riddle riddle) {
        return repository.save(riddle);
    }

    public void delete(Riddle riddle) {
        repository.delete(riddle);
    }

}
