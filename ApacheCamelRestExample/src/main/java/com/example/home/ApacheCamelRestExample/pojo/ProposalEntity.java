package com.example.home.ApacheCamelRestExample.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "PROPOSTA")
@Entity
@NamedQuery(name = "findAllProposta", query = "SELECT b FROM ProposalEntity b")
@ToString
public class ProposalEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PROPOSTA")
	private Integer proposalId;

	@JsonProperty("cpf")
	@Column(name = "CPF", length = 11)
	private String cpf;

	@JsonProperty("name")
	@Column(name = "NOME", length = 100)
	private String name;

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public Integer getProposalId() {
		return proposalId;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(final String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	public void setProposalId(final Integer proposalId) {
		this.proposalId = proposalId;
	}
}
