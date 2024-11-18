package com.sparta.gaeppa.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin/stores")
@RequiredArgsConstructor
@Slf4j
public class StoreAdminController {
    // 1. 가게 전체를 조회하는 일 [ROLE : MANAGER, MASTER] -> StoreAdminController
    // 2. 가게를 아예 삭제하는 일 [ROLE : MANAGER, MASTER] -> StoreAdminController


}
