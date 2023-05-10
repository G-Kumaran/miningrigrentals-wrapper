package me.gkumaran.miningrigrentals.domain.rig;

import java.util.ArrayList;

import lombok.Data;

@Data
public class RigPool
{
	private Long id;
	private Integer rigid;
	private ArrayList<me.gkumaran.miningrigrentals.domain.pool.Pool> pools;
	private Boolean success;
	private String message;
}
