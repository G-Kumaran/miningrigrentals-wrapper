package me.gkumaran.miningrigrentals.domain.account.inputs;

import lombok.Builder;
import lombok.Data;
import me.gkumaran.miningrigrentals.domain.account.TYPE;

@Data
@Builder
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionsFilter
{
	private Integer start;
	private Integer limit;
	private String algo;
	private TYPE type;
	private Integer rental;
	private Integer rig;
	private String txid;
}
