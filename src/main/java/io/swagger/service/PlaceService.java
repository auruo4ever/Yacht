package io.swagger.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import io.swagger.model.Place;

public interface PlaceService {
    public Place addPlace(Place Place);
    public Place deletePlaceById(long rollNo);
	public List<Place> findAllPlaces();
	public Place findPlaceById(long id);
}
