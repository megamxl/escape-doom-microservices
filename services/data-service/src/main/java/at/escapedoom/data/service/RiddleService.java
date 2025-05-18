package at.escapedoom.data.service;

import at.escapedoom.data.data.LevelRepository;
import at.escapedoom.data.data.RiddleRepository;
import at.escapedoom.data.data.entity.Level;
import at.escapedoom.data.data.entity.Riddle;
import at.escapedoom.data.mapper.RiddleMapper;
import at.escapedoom.data.rest.model.RiddleCreationRequestDTO;
import at.escapedoom.data.rest.model.RiddleDTO;
import at.escapedoom.data.rest.model.RiddleDeletionResponseDTO;
import at.escapedoom.data.utils.LoggerUtils;
import at.escapedoom.data.utils.ReflectionUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static at.escapedoom.data.utils.LoggerUtils.logCreation;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiddleService {

    private final RiddleRepository riddleRepository;
    private final RiddleMapper riddleMapper;
    private final LevelRepository levelRepository;

    public List<RiddleDTO> getAllRiddles() {
        return riddleRepository.findAll().stream().map(riddleMapper::toDTO).toList();
    }

    public RiddleDTO getRiddleById(@NonNull String uuid) {
        Riddle riddle = riddleRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new NoSuchElementException("Riddle with ID: " + uuid + " not found"));

        log.info("Riddle {} found", riddle.getRiddleId());

        return riddleMapper.toDTO(riddle);
    }

    @Transactional(rollbackFor = Exception.class)
    public RiddleDTO createRiddle(RiddleCreationRequestDTO riddleRequest) {
        assert riddleRequest != null : "Creation request cannot be null";
        assert riddleRequest.getLevelId() != null : "Level id cannot be null";

        UUID levelUuid = UUID.fromString(riddleRequest.getLevelId());

        Level level = levelRepository.findById(levelUuid)
                .orElseThrow(() -> new IllegalArgumentException("Level not found"));

        if (level.getRiddle() != null) {
            throw new IllegalArgumentException(String.format("Riddle already exists on level %s", level.getLevelId()));
        }

        Riddle riddle = riddleMapper.toEntity(riddleRequest);

        riddle.setLevel(level);

        riddle = riddleRepository.saveAndFlush(riddle);

        level.setRiddle(riddle);
        levelRepository.saveAndFlush(level);

        riddleLog(LoggerUtils.LogType.CREATION, riddle.getRiddleId());
        return riddleMapper.toDTO(riddle);
    }

    @Transactional(rollbackFor = Exception.class)
    public RiddleDTO updateRiddle(RiddleCreationRequestDTO riddleDTO, String uuid) {
        assert riddleDTO != null : "Riddle update request must not be null";
        assert uuid != null : "Riddle UUID must not be null";

        Riddle riddle = riddleRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new IllegalArgumentException(String.format("Riddle with ID: %s not found", uuid)));

        ReflectionUtils.copyNonNullProperties(riddle, riddleDTO);
        riddleRepository.save(riddle);

        riddleLog(LoggerUtils.LogType.UPDATE, riddle.getRiddleId());

        return riddleMapper.toDTO(riddle);
    }

    public RiddleDeletionResponseDTO deleteRiddle(String uuid) {
        assert uuid != null : "UUID must not be null";

        UUID riddleId = UUID.fromString(uuid);

        Riddle riddle = riddleRepository.findById(riddleId)
                .orElseThrow(() -> new NoSuchElementException("Riddle with ID " + riddleId + " not found"));

        Level level = riddle.getLevel();
        if (level != null) {
            level.setRiddle(null);
            levelRepository.save(level);
        }

        riddleRepository.deleteById(riddleId);

        riddleLog(LoggerUtils.LogType.DELETION, riddleId);

        return new RiddleDeletionResponseDTO("Riddle deleted successfully");
    }

    private void riddleLog(LoggerUtils.LogType logType, UUID uuid) {
        logCreation(log, logType, uuid, Riddle.class);
    }
}
