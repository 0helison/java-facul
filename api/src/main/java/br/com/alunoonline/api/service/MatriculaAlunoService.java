package br.com.alunoonline.api.service;

import br.com.alunoonline.api.enums.MatriculaAlunoStatusEnum;
import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.AlunoRepository;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
public class MatriculaAlunoService {

    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    public void create(MatriculaAluno matriculaAluno) {
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public List<MatriculaAluno> findAll () {
        return matriculaAlunoRepository.findAll();
    }

    public Optional<MatriculaAluno> findById(Long id) {
        return matriculaAlunoRepository.findById(id);
    }

    public void update(Long id, MatriculaAluno matriculaAluno) {
        Optional<MatriculaAluno> matriculaAlunoFromDb = findById(id);

        if (matriculaAlunoFromDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula não encontrado no banco de dados!");
        }

        MatriculaAluno matriculaAlunoUpdated = matriculaAlunoFromDb.get();



        matriculaAlunoRepository.save(matriculaAlunoUpdated);
    }

    public  void deleteById(long id) {
        matriculaAlunoRepository.deleteById(id);
    }
}
