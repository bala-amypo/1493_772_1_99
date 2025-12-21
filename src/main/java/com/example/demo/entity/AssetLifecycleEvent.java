package com.example.demo.entity;


import jakarta.persistence.*;
import java.time.*;


@Entity
@Table(name = "asset_lifecycle_events")
public class AssetLifecycleEvent {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


private String eventType;
private String eventDescription;
private LocalDate eventDate;
private LocalDateTime loggedAt;


@ManyToOne
private Asset asset;


public AssetLifecycleEvent() {}


public void setAsset(Asset asset) { this.asset = asset; }
public void setLoggedAt(LocalDateTime t) { this.loggedAt = t; }
public String getEventType() { return eventType; }
public String getEventDescription() { return eventDescription; }
public LocalDate getEventDate() { return eventDate; }
}