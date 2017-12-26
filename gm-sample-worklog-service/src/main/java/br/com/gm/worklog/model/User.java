
package br.com.gm.worklog.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;

import lombok.Data;

@Data
@Entity
@Table(name="user")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User { 

  @Id
  @GeneratedValue
  @Column(name="userid")
  private Long userId;

  @Column(name="username")
  private String userName;

  @Column(name="userhash")
  private String userHash;

  @Column(name="userlogin")
  private String userLogin;

  // the database could create it, but JPA have some limitations
  // @Column(name="usercreation")
  // private Date userCreation = Calendar.getInstance().setTimeInMillis(System.currentTimeMillis());
}