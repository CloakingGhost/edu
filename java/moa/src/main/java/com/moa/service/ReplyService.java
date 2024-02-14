package com.moa.service;

import com.moa.domain.Comment;
import com.moa.domain.Reply;
import com.moa.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyService {
    private final ReplyRepository replyRepository;

    @Transactional
    public void save(Reply reply) {
        replyRepository.save(reply);
    }

    public List<Reply> findAll() {
        return replyRepository.findAll();
    }

    public List<Reply> findAllById(Long boardId) {
        return replyRepository.findAllById(boardId);
    }
}
