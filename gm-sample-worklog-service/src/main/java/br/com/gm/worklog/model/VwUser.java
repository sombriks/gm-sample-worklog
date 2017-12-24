
package br.com.gm.worklog.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.sql.Timestamp;

import lombok.Data;

@Data
@Entity
@Table(name="vwuser")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VwUser { 

  @Id
  @Column(name="userid")
  private Long userId;

  @Column(name="username")
  private String userName;

  @Column(name="userlogin")
  private String userLogin;

  @Column(name="usercreation")
  private Timestamp userCreation;
}