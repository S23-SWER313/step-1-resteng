package com.resteng.resteng.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.resteng.resteng.repo.CartRepo;

@Service
public class CartService {

    @Autowired
    private CartRepo repo;

}
