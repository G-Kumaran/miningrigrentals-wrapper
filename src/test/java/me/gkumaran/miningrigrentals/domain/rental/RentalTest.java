package me.gkumaran.miningrigrentals.domain.rental;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;
import me.gkumaran.miningrigrentals.constant.TYPE;

@Slf4j
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
		lombok.val response = miningRigRentals.getRental(3463059);
		log.info("Response : {}", response);
		assertThat(response).isInstanceOf(me.gkumaran.miningrigrentals.domain.rental.Rental.class);
	}

}
