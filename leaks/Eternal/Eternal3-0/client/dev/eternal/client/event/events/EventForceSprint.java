package dev.eternal.client.event.events;

import dev.eternal.client.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EventForceSprint extends AbstractEvent {

  private boolean forced;

}
