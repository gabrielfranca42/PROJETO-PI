package Service;

import Model.SementeModel;
import Repository.SementeRepository;
import Exceptions.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SementeService {

    private final SementeRepository sementeRepository;

    public SementeService(SementeRepository sementeRepository) {
        this.sementeRepository = sementeRepository;
    }

    public List<SementeModel> listarSementes() {
        return sementeRepository.findAll();
    }

    public SementeModel buscarPorId(Long id) {
        return sementeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Semente com ID " + id + " não encontrada"));
    }

    public SementeModel salvarSemente(SementeModel semente) {
        return sementeRepository.save(semente);
    }

    public void deletarSemente(Long id) {
        if (!sementeRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Semente com ID " + id + " não encontrada.");
        }
        sementeRepository.deleteById(id);
    }
}
