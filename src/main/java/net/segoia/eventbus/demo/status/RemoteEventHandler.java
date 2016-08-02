package net.segoia.eventbus.demo.status;

import net.segoia.event.eventbus.peers.EventBusNode;

public interface RemoteEventHandler<N extends EventBusNode> {
     void handleRemoteEvent(RemoteEventContext<N> rec);
}
