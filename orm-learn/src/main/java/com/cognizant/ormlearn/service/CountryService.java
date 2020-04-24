package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.exception.CountryNotFoundException;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;

@Service
public class CountryService {
	@Autowired
	private CountryRepository countryRepository;
	
	@Transactional
	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}
	
	@Transactional
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
		Optional<Country> result= countryRepository.findById(countryCode);
		if(!result.isPresent()) {
			throw new CountryNotFoundException("Country not found");
		}else {
			return result.get();
		}
	}
	
	@Transactional
	public void addCountry(Country country) {
		countryRepository.save(country);
	}
	
	@Transactional
	public void updateCountry(Country country) {
		Country countryFromDb=countryRepository.findById(country.getCountryCode()).orElse(new Country());
		countryFromDb.setCountryCode(country.getCountryCode());
		countryFromDb.setCountryName(country.getCountryName());
		countryRepository.save(countryFromDb);
	}
	
	@Transactional
	public void deleteCountry(String countryCode) {
		countryRepository.deleteById(countryCode);
	}
}
