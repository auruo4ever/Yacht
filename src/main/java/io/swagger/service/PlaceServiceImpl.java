package io.swagger.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.swagger.model.Place;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {
	@Autowired
	private PlaceRepository placeRepository;

	@Override
	public Place addPlace(Place Place) {
		return placeRepository.save(Place);
	}	

	@Override
	public Place deletePlaceById(long id) {
		Optional<Place> os = placeRepository.findById(id);
		if(os.isPresent()) placeRepository.deleteById(id);
		return os.get();
	}

	@Override
	public List<Place> findAllPlaces() {
		return placeRepository.findAll();
	}

	@Override
	public Place findPlaceById(long id) {
		Optional<Place> os = placeRepository.findById(id);
		if(os.isPresent()) return os.get();
		else return null;
	}

}
