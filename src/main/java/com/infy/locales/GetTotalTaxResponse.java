
package com.infy.locales;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/**
 * PoJO to return response as in Bigdecimal so that api will return in fractional value rather than in string
 * @author Abinash
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "CGST", "SGST", "TotalTax" })
public class GetTotalTaxResponse {

	@JsonProperty("CGST")
	private BigDecimal cGST;
	@JsonProperty("SGST")
	private BigDecimal sGST;
	@JsonProperty("TotalTax")
	private BigDecimal totalTax;
	@JsonProperty("commison")
	private BigDecimal commison;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("CGST")
	public BigDecimal getCGST() {
		return cGST;
	}

	@JsonProperty("CGST")
	public void setCGST(BigDecimal cGST) {
		this.cGST = cGST;
	}

	@JsonProperty("SGST")
	public BigDecimal getSGST() {
		return sGST;
	}

	@JsonProperty("SGST")
	public void setSGST(BigDecimal sGST) {
		this.sGST = sGST;
	}

	@JsonProperty("TotalTax")
	public BigDecimal getTotalTax() {
		return totalTax;
	}

	@JsonProperty("TotalTax")
	public void setTotalTax() {
		this.totalTax = cGST.add(sGST).setScale(2, BigDecimal.ROUND_UNNECESSARY);
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@JsonProperty("commison")
	public BigDecimal getCommison() {
		return commison;
	}
	
	@JsonProperty("commison")
	public void setCommison() {
		if (this.totalTax.longValue()<100)
			this.commison = new BigDecimal(0).setScale(2, BigDecimal.ROUND_UNNECESSARY);
		else
			this.commison = this.totalTax.multiply(new BigDecimal(5)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UNNECESSARY);
	}

}