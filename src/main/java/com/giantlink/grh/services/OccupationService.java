package com.giantlink.grh.services;

import com.giantlink.grh.entities.Occupation;

import java.util.List;

public interface OccupationService {
    List<Occupation> get();
    Occupation get(Integer id);
    Occupation add(Occupation occupation);
    void delete(Integer id);
}
