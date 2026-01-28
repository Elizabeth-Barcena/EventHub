package elizabethbarcena.EventHubAPI.service.impl;

import elizabethbarcena.EventHubAPI.dto.request.ParticipantRequest;
import elizabethbarcena.EventHubAPI.dto.response.ParticipantResponse;
import elizabethbarcena.EventHubAPI.entity.Participant;
import elizabethbarcena.EventHubAPI.exceptions.ParticipantNotFoundException;
import elizabethbarcena.EventHubAPI.mapper.ParticipantMapper;
import elizabethbarcena.EventHubAPI.repository.ParticipantRepository;
import elizabethbarcena.EventHubAPI.service.ParticipantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public ParticipantResponse createOrGetParticipant(ParticipantRequest request) {
        Participant participant = participantRepository
                .findByEmail(request.getEmail())
                .orElseGet(() -> {
                    Participant newParticipant = ParticipantMapper.toEntity(request);
                    return participantRepository.save(newParticipant);
                });

        return ParticipantMapper.toResponse(participant);
    }

    @Override
    public ParticipantResponse getParticipantById(Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() ->
                        new ParticipantNotFoundException(id)
                );

        return ParticipantMapper.toResponse(participant);
    }

    @Override
    public ParticipantResponse getParticipantByEmail(String email) {
        Participant participant = participantRepository.findByEmail(email)
                .orElseThrow(() ->
                        new  ParticipantNotFoundException(email));

        return ParticipantMapper.toResponse(participant);
    }

    @Override
    public List<ParticipantResponse> getAllParticipants() {
        return participantRepository.findAll()
                .stream()
                .map(ParticipantMapper::toResponse)
                .collect(Collectors.toList());
    }
}