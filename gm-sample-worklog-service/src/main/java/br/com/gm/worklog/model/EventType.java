
package br.com.gm.worklog.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

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

  public EventType(){}

  public EventType(EventTypes type) { this.eventTypeId = type.getId(); }

}