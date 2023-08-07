package com.bid.idearush.domain.idea.controller;

import com.bid.idearush.domain.idea.model.reponse.IdeaResponse;
import com.bid.idearush.domain.idea.service.IdeaFindService;
import com.bid.idearush.domain.idea.type.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ideas")
public class IdeaFindController {

    private final IdeaFindService ideaFindService;

    @GetMapping
    public List<IdeaResponse> findAllIdea(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Category category
    ) {
        return ideaFindService.findAllIdea(keyword, category);
    }

    @GetMapping("/{id}")
    public IdeaResponse findOneQuery(
            @PathVariable Long id
    ) {
        return ideaFindService.findOneIdea(id);
    }

}