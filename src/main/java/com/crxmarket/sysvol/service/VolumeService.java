package com.crxmarket.sysvol.service;

import java.util.List;

import javax.ejb.Local;


import com.crxmarket.sysvol.model.Volume;

@Local
public interface VolumeService {
	
	Volume calcVolume(List<Integer> columns);

}
