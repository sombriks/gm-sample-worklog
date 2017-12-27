package br.com.gm.worklog.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name="worklog")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkLog {
  
  @Id
  @GeneratedValue
  @Column(name="worklogid")
  private Long workLogId; 

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="worklogstart")
  private Date workLogStart;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="worklogfinish")
  private Date workLogFinish;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name="worklogcreation")
  private Date workLogCreation = new Date(); // Calendar(System.currentTimeMillis());

  @ManyToOne
  @JoinColumn(name="logstatusid")
  private LogStatus status;
  
  @ManyToOne
  @JoinColumn(name="userid")
  private VwUser user;
}