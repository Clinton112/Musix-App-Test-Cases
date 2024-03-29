package com.stackroute.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import com.stackroute.service.TrackServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TrackServiceTest {



    Track track;

    @Mock
    TrackRepository trackRepository;

    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list = null;

    @Before
    public void setUp() {
      MockitoAnnotations.initMocks(this);
      track = new Track();
      track.setId(11);
      track.setName("Rihana");
      track.setComment("Good song");
      list = new ArrayList<>();
      list.add(track);
    }


    @Test
    public void saveTrackTest() throws TrackAlreadyExistsException
    {
      when(trackRepository.save((Track)any())).thenReturn(track);
      Track savedTrack = trackService.saveTrack(track);
      Assert.assertEquals(track,savedTrack);
    }

    @Test
    public void getTrackTest()
    {
      trackRepository.save(track);
      when(trackRepository.findAll()).thenReturn(list);
      List<Track> trackList = trackService.getAllTracks();
      Assert.assertEquals(list,trackList);
    }

    @Test
    public void updateTrackTest() throws TrackNotFoundException
    {
      when(trackRepository.save((Track)any())).thenReturn(track);
      Track updateTrack = null;
      try {
        updateTrack = trackService.saveTrack(track);
      } catch (TrackAlreadyExistsException e) {
        e.printStackTrace();
      }
      Assert.assertEquals(track,updateTrack);
    }
  }



