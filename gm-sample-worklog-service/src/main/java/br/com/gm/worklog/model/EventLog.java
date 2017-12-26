
package br.com.gm.worklog.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;

import lombok.Data;

@Data
@Entity
@Table(name="eventlog")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventLog {

  @Id 
  @GeneratedValue
  @Column(name="eventlogid")
  private Long eventLogId;

  @Column(name="eventlogcreation")
  private Date eventLogCreation = new Date();

  @ManyToOne
  @JoinColumn(name="userid")
  private VwUser user;

  @ManyToOne
  @JoinColumn(name="eventtypeid")
  private EventType type;

  @Column(name="eventlogdescription")
  private String eventLogDescription;
}