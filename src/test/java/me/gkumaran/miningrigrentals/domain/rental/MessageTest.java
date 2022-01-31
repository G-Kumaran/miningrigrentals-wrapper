package me.gkumaran.miningrigrentals.domain.rental;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;
import me.gkumaran.miningrigrentals.domain.common.Success;
import me.gkumaran.miningrigrentals.domain.rental.Messages.Message;
import me.gkumaran.miningrigrentals.domain.rental.inputs.MessageConfig;

@Slf4j
class MessageTest extends ApiClientTest
{
	@Test
	void getMessageTest()
	{
		final lombok.val response = miningRigRentals.getRentalMessage(3610679);
		log.info("Response : {}", response);
		assertThat(response).isInstanceOf(Messages.class);
		assertThat(response .getMessages()
							.get(0)).isInstanceOf(Message.class);
	}

	@Test
	void getMessagesTest()
	{
		final lombok.val response = miningRigRentals.getRentalMessage(3610679, 3617887);
		log.info("Response : {}", response);
		assertThat(response).isInstanceOf(ArrayList.class);
		assertThat(response.get(1)).isInstanceOf(Messages.class);
		assertThat(response .get(1)
							.getMessages()
							.get(0)).isInstanceOf(Message.class);
	}

	@Disabled
	@Test
	void putMessageTest()
	{
		final lombok.val response = miningRigRentals.putRentalMessage(MessageConfig .builder()
																					.message("Thx for the rental mate !!")
																					.build(), 3617887);
		assertThat(response).isInstanceOf(Success.class);
		log.info("Response : {}", response);
	}
}
