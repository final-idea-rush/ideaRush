package com.bid.idearush.domain.user.model.entity;

import com.bid.idearush.global.model.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16, nullable = false)
    private String nickname;

    @Column(length = 16, nullable = false)
    private String userAccountId;

    @Column(length = 32, nullable = false)
    private String password;
}