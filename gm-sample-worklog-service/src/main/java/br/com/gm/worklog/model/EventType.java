
package br.com.gm.worklog.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import java.sql.Timestamp;

import lombok.Data;

@Data
@Entity
@Table(name="eventtype") 
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EventType {
  
  @Id
  @Column(name="eventtypeid")
  private Long eventTypeId;

  @Column(name="eventtypedescription")
  private String eventTpeDescription;

}