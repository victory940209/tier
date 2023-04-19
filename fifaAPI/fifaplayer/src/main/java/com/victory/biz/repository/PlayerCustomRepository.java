package com.victory.biz.repository;

import java.util.List;

import com.victory.biz.model.PlayerVo;

public interface PlayerCustomRepository {

    List<PlayerVo> findAll();
    PlayerVo findBysearch(PlayerVo playerVo);
}
