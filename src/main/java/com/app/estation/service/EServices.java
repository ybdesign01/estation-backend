package com.app.estation.service;

import java.util.List;

public interface EServices<T,E> {
        T add(E request);
        T get(Long id);
        T update(E request);
        boolean delete(Long id);
        List<T> getAll();
}
