package com.crxmarket.sysvol.service;

import javax.ejb.Local;


import com.crxmarket.sysvol.model.Volume;

@Local
public interface VolumeService {
	
	Volume calcVolume(String data);

}
