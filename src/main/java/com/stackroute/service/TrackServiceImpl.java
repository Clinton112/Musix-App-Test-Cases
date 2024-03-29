package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    TrackRepository trackRepository;
   // Track track;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {

        if(trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("Track Already Exists");
        }
        Track savedTrack = trackRepository.save(track);

        if(savedTrack == null) {
            throw new TrackAlreadyExistsException("Track Already Exists");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id) {

        Track track = trackRepository.findById(id).get();
        return track;

    }

    @Override
    public boolean deleteTrack(int id) throws TrackNotFoundException {

        if(!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("No track found with given ID");
        }

        trackRepository.delete(getTrackById(id));
        return true;

    }

    @Override
    public Track updateTrack(Track track) throws TrackNotFoundException {

      if(trackRepository.existsById(track.getId()))
      {

        Track updateTrack=trackRepository.save(track);
        return updateTrack;


      }

      else {

        throw new TrackNotFoundException("ID doesnt exist");
      }



    }



    @Override
    public List<Track> getByTrackName(String name) {
        return trackRepository.findByName(name);
    }

    @Override
    public List<Track> getByTrackNameSortByName(String name) {
        return trackRepository.findByNameSortById(name);
    }

}
