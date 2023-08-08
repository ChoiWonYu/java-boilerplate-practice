package com.example.boilerplate.board.repository;

import com.example.boilerplate.board.entity.Board;
import com.example.boilerplate.common.response.PageResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, UUID> {
    Page<Board> findByTitleContaining(String keyword, Pageable pageRequest);
}
