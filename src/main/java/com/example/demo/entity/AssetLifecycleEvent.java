package com.example.demo.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AssetLifecycleEvent {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Asset asset;

    private String eventType;
    private String eventDescription;
    private LocalDate eventDate;
    private LocalDateTime loggedAt = LocalDateTime.now();

    public void setAsset(Asset a){ asset=a; }
    public void setEventType(String t){ eventType=t; }
    public void setEventDescription(String d){ eventDescription=d; }
    public void setEventDate(LocalDate d){ eventDate=d; }
}
