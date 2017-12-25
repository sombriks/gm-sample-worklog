
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
@Table(name="logstatus") 
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LogStatus {
  
  @Id
  @Column(name="logstatusid")
  private Long logStatusId;

  @Column(name="logstatusdescription")
  private String logStatusDescription;

}