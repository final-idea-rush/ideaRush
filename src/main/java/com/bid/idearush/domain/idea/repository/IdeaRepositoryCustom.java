package com.bid.idearush.domain.idea.repository;

import com.bid.idearush.domain.idea.model.reponse.IdeaListResponse;
import com.bid.idearush.domain.idea.model.reponse.IdeaOneResponse;
import com.bid.idearush.domain.idea.type.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IdeaRepositoryCustom {

    Page<IdeaListResponse> findIdeaAll(Pageable pageable, long count);
    Page<IdeaListResponse> findCategoryAndTitleAll(Category category, String keyword, Pageable pageable, long count);
    Optional<IdeaOneResponse> findIdeaOne(Long ideaId);

}
