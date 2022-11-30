package com.ntigo.mockito.example;

import java.util.List;

public interface MockRepository {
    List<Mock> findAll();
//    List<Mock> findAll(Sort sort);
}
