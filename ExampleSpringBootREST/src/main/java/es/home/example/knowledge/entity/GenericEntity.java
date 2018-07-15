package es.home.example.knowledge.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class GenericEntity<K extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected K id;
	protected Date creationDate;
	protected Date editDate;
	protected Integer creationUser;
	protected Integer editUser;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	protected K getId() {
		return id;
	}

	protected void setId(final K id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_TIMESTAMP")
	@CreatedDate
	public Date getInsertDate() {
		return creationDate;
	}

	public void setInsertDate(final Date creationDate) {
		this.creationDate = creationDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIMESTAMP")
	@LastModifiedDate
	public Date getUpdateDate() {
		return editDate;
	}

	public void setUpdateDate(final Date editDate) {
		this.editDate = editDate;
	}

	@Column(name = "INSERT_USER")
	@CreatedBy
	public Integer getInsertUser() {
		return creationUser;
	}

	public void setInsertUser(final Integer creationUser) {
		this.creationUser = creationUser;
	}

	@Column(name = "UPDATE_USER")
	@LastModifiedBy
	public Integer getUpdateUser() {
		return editUser;
	}

	public void setUpdateUser(final Integer editUser) {
		this.editUser = editUser;
	}

}
