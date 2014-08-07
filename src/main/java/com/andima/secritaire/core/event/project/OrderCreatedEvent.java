package com.andima.secritaire.core.event.project;

import com.andima.secritaire.core.event.CreatedEvent;

import java.util.UUID;

public class OrderCreatedEvent extends CreatedEvent {

  private final UUID newOrderKey;
  private final ProjectDetail details;

  public OrderCreatedEvent(final UUID newOrderKey, final ProjectDetail details) {
    this.newOrderKey = newOrderKey;
    this.details = details;
  }

  public ProjectDetail getDetails() {
    return details;
  }

  public UUID getNewOrderKey() {
    return newOrderKey;
  }
}
