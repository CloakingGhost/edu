package com.moa.service;

import com.moa.domain.Board;
import com.moa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void save(Board board) {
        boardRepository.save(board);
    }

    public Board findOne(Long boardId) {
        return boardRepository.findById(boardId);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }
}
