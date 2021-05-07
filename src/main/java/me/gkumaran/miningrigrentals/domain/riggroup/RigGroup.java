package me.gkumaran.miningrigrentals.domain.riggroup;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import me.gkumaran.miningrigrentals.domain.rig.Rig;

@Data
public class RigGroup
{
	private Long id;
	private String name;
	private String enabled;
	@JsonProperty("rental_limit")
	private Long rentalLimit;
	@JsonProperty("active_rentals")
	private ArrayList<String> activeRentals;
	private ArrayList<Rig> rigs;
}
