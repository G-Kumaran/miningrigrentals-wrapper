package me.gkumaran.miningrigrentals.domain.rental;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import me.gkumaran.miningrigrentals.ApiClientTest;
import me.gkumaran.miningrigrentals.constant.TYPE;

class RentalTest extends ApiClientTest
{
	@Test
	void getRentalsTest()
	{
		final me.gkumaran.miningrigrentals.domain.rental.inputs.RentalFilter rentalFilter = me.gkumaran.miningrigrentals.domain.rental.inputs.RentalFilter  .builder()
																																							.type(TYPE.OWNER)
																																							.algo("kawpow")
																																							.history(true)
																																							.build();
		assertThat(miningRigRentals.getRentals(rentalFilter)).isInstanceOf(me.gkumaran.miningrigrentals.domain.rental.Rentals.class);
		assertThat(miningRigRentals .getRentals(rentalFilter)
									.getRentals()
									.get(0)).isInstanceOf(me.gkumaran.miningrigrentals.domain.rental.Rental.class);
	}

	@Test
	void getRentalsBookedTest()
	{
		assertThat(miningRigRentals .getRentalsBooked()
									.get(0)).isInstanceOf(me.gkumaran.miningrigrentals.domain.rental.Rental.class);
	}

}
