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

import java.sql.Timestamp;

import lombok.Data;

@Data
@Entity
@Table(name="vwuser")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkLog {
  
  @Id
  @Column(name="worklogid")
  private Long workLogId;

  @Column(name="worklogstart")
  private Timestamp workLogStart;

  @Column(name="worklogfinish")
  private Timestamp workLogFinish;

  @Column(name="worklogcreation")
  private Timestamp workLogCreation;

  @ManyToOne
  @JoinColumn(name="logstatusid")
  private LogStatus status;
  
  @ManyToOne
  @JoinColumn(name="userid")
  private VwUser user;
}